package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.entity.Book;
import com.haruncinar.readingisgood.entity.Customer;
import com.haruncinar.readingisgood.entity.Order;
import com.haruncinar.readingisgood.entity.OrderLine;
import com.haruncinar.readingisgood.entity.Stock;
import com.haruncinar.readingisgood.model.CreateNewOrderResponse;
import com.haruncinar.readingisgood.model.GetAllCustomerOrdersByIdWithPaginationResponse;
import com.haruncinar.readingisgood.model.GetOrderByDateIntervalResponse;
import com.haruncinar.readingisgood.model.GetOrderByIdResponse;
import com.haruncinar.readingisgood.model.dto.OrderDTO;
import com.haruncinar.readingisgood.model.dto.OrderLineDTO;
import com.haruncinar.readingisgood.model.enums.OrderStatus;
import com.haruncinar.readingisgood.model.exception.BookStockNotEnoughException;
import com.haruncinar.readingisgood.model.exception.BookStockNotFoundException;
import com.haruncinar.readingisgood.model.exception.OrderDateFormatException;
import com.haruncinar.readingisgood.model.exception.OrderValidateException;
import com.haruncinar.readingisgood.repository.OrderRepository;
import com.haruncinar.readingisgood.util.ReadingIsGoodConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class OrderService
{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private BookService bookService;

    public GetAllCustomerOrdersByIdWithPaginationResponse findAllCustomerOrdersByIdWithPagination(String id, int pageNo, int pageSize)
    {
        try
        {
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            List<Order> orderList = orderRepository.findByCustomerId(id, pageable);
            return convertOrderToGetAllCustomerOrdersByIdWithPaginationResponse(orderList);
        }
        catch (Exception exception)
        {
            GetAllCustomerOrdersByIdWithPaginationResponse response = new GetAllCustomerOrdersByIdWithPaginationResponse();
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public GetOrderByIdResponse findOrderById(String id)
    {
        GetOrderByIdResponse response = new GetOrderByIdResponse();
        try
        {
            Order order = orderRepository.findById(id).orElse(null);
            if( order != null)
            {
                return convertOrderToGetOrderByIdResponse(order);
            }
            response.setMessage(String.format(ReadingIsGoodConstants.BOOK_NOT_FOUND_MESSAGE, id));
        }
        catch (Exception exception)
        {
            response.setMessage(exception.getMessage());
        }
        return response;
    }

    @Transactional
    public CreateNewOrderResponse createNewOrder(Order order)
    {
        try
        {
            Customer customer = customerService.findCustomerById(order.getCustomer().getId());
            validateOrder(order, customer);
            List<Stock> willBeUpdatedStockList = new ArrayList<>();
            handleStocksForOrderLines(order.getOrderLines(), willBeUpdatedStockList);
            orderLineService.saveOrderLines(order.getOrderLines());
            stockService.saveStockList(willBeUpdatedStockList);
            order = orderRepository.save(order);
            customer.settingOrder(customer, order);
            customerService.saveCustomer(customer);
            return convertOrderToCreateNewOrderResponse(order);
        }
        catch (Exception exception)
        {
            CreateNewOrderResponse response = new CreateNewOrderResponse();
            response.setMessage(exception.getMessage());
            return response;
        }

    }

    public GetOrderByDateIntervalResponse findOrdersByDateInterval(String startDate, String endDate)
    {
        try
        {
            validateOrderDates(startDate, endDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = simpleDateFormat.parse(startDate);
            Date end = simpleDateFormat.parse(endDate);
            List<Order> orderList = orderRepository.findOrdersByDateInterval(start, end);
            return convertOrderToGetOrderByDateIntervalResponse(orderList);
        }
        catch (Exception exception)
        {
            GetOrderByDateIntervalResponse response = new GetOrderByDateIntervalResponse();
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public List<Object[]> findStatistics()
    {
        return orderRepository.findStatistics();
    }

    private void validateOrder(Order order, Customer customer)
    {
        if (customer == null)
        {
            throw new OrderValidateException(ReadingIsGoodConstants.CUSTOMER);
        }
        if (order.getOrderLines() == null || order.getOrderLines().isEmpty())
        {
            throw new OrderValidateException(ReadingIsGoodConstants.ORDER_LINE);
        }
        if (customer.getAddress() == null)
        {
            throw new OrderValidateException(ReadingIsGoodConstants.ADDRESS);
        }
        List<OrderLine> wrongAmountOrderLine = order.getOrderLines().stream().filter(orderLine -> orderLine.getAmount() < 0).collect(Collectors.toList());
        if (!wrongAmountOrderLine.isEmpty())
        {
            throw new OrderValidateException(ReadingIsGoodConstants.AMOUNT);
        }
    }

    private void validateStock(Stock stock, OrderLine orderLine, Book book)
    {
        if(stock == null)
        {
            throw new BookStockNotFoundException(book.getId());
        }
        if(stock.getAmount() < orderLine.getAmount())
        {
            throw new BookStockNotEnoughException();
        }
    }

    private void validateOrderDates(String startDate, String endDate)
    {
        Pattern patternStartDate = Pattern.compile(ReadingIsGoodConstants.DATE_FORMAT);
        Matcher matcherStartDate = patternStartDate.matcher(startDate);
        Pattern patternEndDate = Pattern.compile(ReadingIsGoodConstants.DATE_FORMAT);
        Matcher matcherEndDate = patternEndDate.matcher(endDate);
        if(!matcherStartDate.matches() || !matcherEndDate.matches())
        {
            throw new OrderDateFormatException();
        }
    }

    private void handleStocksForOrderLines(List<OrderLine> orderLines, List<Stock> stockList)
    {
        for (OrderLine orderLine : orderLines)
        {
            Book book = bookService.findBookById(orderLine.getBook().getId());
            if (book == null)
            {
                throw new OrderValidateException(ReadingIsGoodConstants.BOOK);
            }
            Stock stock = stockService.findStockByBook(book.getId());
            validateStock(stock, orderLine, book);
            stock.handleNewAmount(orderLine.getAmount());
            stockList.add(stock);
        }
    }

    private GetOrderByDateIntervalResponse convertOrderToGetOrderByDateIntervalResponse(List<Order> orderList)
    {
        GetOrderByDateIntervalResponse response = new GetOrderByDateIntervalResponse();
        response.setOrders(new ArrayList<>());
        for(Order order : orderList)
        {
            OrderDTO orderDTO = convertOrderToOrderDTO(order);
            response.getOrders().add(orderDTO);
        }
        return response;
    }

    private CreateNewOrderResponse convertOrderToCreateNewOrderResponse(Order order)
    {
        CreateNewOrderResponse response = new CreateNewOrderResponse();
        OrderDTO orderDTO = convertOrderToOrderDTO(order);
        response.setOrderDTO(orderDTO);
        return response;
    }

    private GetOrderByIdResponse convertOrderToGetOrderByIdResponse(Order order)
    {
        GetOrderByIdResponse response = new GetOrderByIdResponse();
        OrderDTO orderDTO = convertOrderToOrderDTO(order);
        response.setOrderDTO(orderDTO);
        return response;
    }

    private GetAllCustomerOrdersByIdWithPaginationResponse convertOrderToGetAllCustomerOrdersByIdWithPaginationResponse(List<Order> orderList)
    {
        GetAllCustomerOrdersByIdWithPaginationResponse response = new GetAllCustomerOrdersByIdWithPaginationResponse();
        response.setOrders(new ArrayList<>());
        for(Order order : orderList)
        {
            OrderDTO orderDTO = convertOrderToOrderDTO(order);
            response.getOrders().add(orderDTO);
        }
        return response;
    }

    private OrderDTO convertOrderToOrderDTO(Order order)
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setOrderStatus(OrderStatus.NEW);
        if(order.getCustomer() != null)
        {
            orderDTO.setCustomerId(order.getCustomer().getId());
        }
        orderDTO.setOrderLines(new ArrayList<>());
        orderDTO.setCreateTime(order.getCreateTime());
        for(OrderLine orderLine : order.getOrderLines())
        {
            OrderLineDTO orderLineDTO = new OrderLineDTO();
            orderLineDTO.setAmount(orderLine.getAmount());
            orderLineDTO.setBookId(orderLine.getBook().getId());
            orderDTO.getOrderLines().add(orderLineDTO);
        }
        return orderDTO;
    }
}
