package com.haruncinar.readingisgood.repository;

import com.haruncinar.readingisgood.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>
{
    List<Order> findByCustomerId(String id, Pageable pageable);

    @Query("{createTime: { $gt: ?0 , $lt: ?1 } }"
    )
    List<Order> findOrdersByDateInterval(Date startDate, Date endDate);

    @Query("{ $group: { " +
            "            month: $createTime," +
            "            totalPurchasedAmount : { $sum: { $multiply: [$orderLine.book.price*$orderLine.amount] } }," +
            "            totalOrderCount: { $count:count }," +
            "            totalBookCount: { $sum:$orderLine.amount }" +
            "          }" +
            "}")
    List<Object[]> findStatistics();
}
