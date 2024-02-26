//package eBook.EatBook.domain.cart.Entity;
//
//
//import eBook.EatBook.global.baseEntity.BaseEntity;
//import jakarta.persistence.Entity;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import org.hibernate.mapping.ToOne;
//
//@Entity
//@Getter
//@SuperBuilder(toBuilder = true)
//@AllArgsConstructor
//@NoArgsConstructor
//public class Cart extends BaseEntity {
//    private Integer total_price;
//    private Integer total_discount;
//
//    @ManyToOne
//    private String member;
//
//    @OneToOne(mappedBy = "book", cascade = CascadeType.REMOVE)
//    private List<book> bookListList;
//}
