package eBook.EatBook.domain.orders.repository;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.orders.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Optional<Orders> findByMember(Member member);
}
