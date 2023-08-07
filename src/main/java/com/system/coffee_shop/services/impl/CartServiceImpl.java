package com.system.coffee_shop.services.impl;

import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.repo.CartRepo;
import com.system.coffee_shop.repo.ProductRepo;
import com.system.coffee_shop.entity.Cart;
//import com.system.kinmel.entity.Sale;
import com.system.coffee_shop.pojo.CartPojo;
//import com.system.kinmel.repo.SaleRepo;
import com.system.coffee_shop.repo.UserRepo;
import com.system.coffee_shop.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

//import static com.system.impl.services.coffee_shop.ProductServiceImpl.getImageBase64;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
//    private final SaleRepo saleRepo;

    @Override
    public String saveToCart(Integer id, Principal principal) {
        Cart cart = new Cart();
        cart.setUser(userRepo.findByEmail(principal.getName()).orElseThrow());
        cart.setProduct(productRepo.findById(id).orElseThrow());
        cart.setQuantity(1);
        cartRepo.save(cart);

        return "Saved";
    }

//    @Override
//    public CartPojo saveToCart(CartPojo cartPojo) {
//        Cart cart = new Cart();
//        if (cartPojo.getId() != null) {
//            cartPojo.setId(cartPojo.getId());
//        }
//        cart.setProduct(productRepo.findById(cartPojo.getProduct_id()).orElseThrow());
//        cart.setUser(userRepo.findById(cartPojo.getUser_id()).orElseThrow());
//
//        cartRepo.save(cart);
//        return new CartPojo();
//    }
    @Override
    public String deleteFromCart(Integer id) {
        cartRepo.deleteById(id);
        return "Deleted";
    }

    @Override
    public String updateQuantity(Cart cart) {
        cartRepo.save(cart);
        return "Updated";
    }

    @Override
    public List<Cart> fetchAll(Integer id) {
        List<Cart> allItems = cartRepo.fetchAll(id).orElseThrow();
        for (Cart cart : allItems){
            cart.setProduct(Product.builder()
                    .id(cart.getProduct().getId())
                    .product_quantity(cart.getProduct().getProduct_quantity())
                    .product_name(cart.getProduct().getProduct_name())
                    .build());
        }
        return allItems;
    }

    public List<Cart> fetchAvailable(Integer id) {
        List<Cart> allItems = cartRepo.fetchAll(id).orElseThrow();
        for (Cart cart : allItems){
            if (cart.getQuantity()<cart.getProduct().getProduct_quantity()) {
                cart.setProduct(Product.builder()
                        .id(cart.getProduct().getId())
                        .product_quantity(cart.getProduct().getProduct_quantity())
                        .product_name(cart.getProduct().getProduct_name())
                        .build());
            } else {
                allItems.remove(cart);
            }
        }
        return allItems;
    }

//    public Double discountedPrice(Product product){
//        List<Sale> sales = saleRepo.saleProducts();
//        for (Sale sale : sales) {
//            if (sale.getProduct().getId().equals(product.getId())) {
//                return product.getProduct_price() - sale.getDiscountPercent()/100 * product.getProduct_price();
//            }
//        }
//        return product.getProduct_price();
//    }

    @Override
    public Cart fetchOne(Integer id) {
        return cartRepo.findById(id).orElseThrow();
    }

    @Override
    public String checkout(Integer id, CartPojo pojo, List<Cart> itemsToPurchase) {
        for (Cart value : itemsToPurchase) {
            Cart cart = new Cart();
            cart.setId(value.getId());
            cart.setUser(value.getUser());
            cart.setProduct(value.getProduct());
            cart.setQuantity(value.getQuantity());
//            cart.setBillingAddress(pojo.getBillingAddress());
//            cart.setBillingApartment(pojo.getBillingApartment());
//            cart.setBillingCompany_name(pojo.getBillingCompany_name());
//            cart.setBillingFirstName(pojo.getBillingFirstName());
//            cart.setBillingLastName(pojo.getBillingLastName());
//            cart.setBillingTown(pojo.getBillingTown());
//            cart.setBillingState(pojo.getBillingState());
//            cart.setBillingEmail(pojo.getBillingEmail());
//            cart.setBillingPhone(pojo.getBillingPhone());
//            cart.setBillingPostal(pojo.getBillingPostal());

//            if (pojo.getDifferentShipping() == null || !pojo.getDifferentShipping()) {
//                cart.setShippingAddress(pojo.getBillingAddress());
//                cart.setShippingApartment(pojo.getBillingApartment());
//                cart.setShippingCompany_name(pojo.getBillingCompany_name());
//                cart.setShippingFirstName(pojo.getBillingFirstName());
//                cart.setShippingLastName(pojo.getBillingLastName());
//                cart.setShippingTown(pojo.getBillingTown());
//                cart.setShippingState(pojo.getBillingState());
//                cart.setShippingEmail(pojo.getBillingEmail());
//                cart.setShippingPhone(pojo.getBillingPhone());
//                cart.setShippingPostal(pojo.getBillingPostal());
//            } else {
//                cart.setShippingAddress(pojo.getShippingAddress());
//                cart.setShippingApartment(pojo.getShippingApartment());
//                cart.setShippingCompany_name(pojo.getShippingCompany_name());
//                cart.setShippingFirstName(pojo.getShippingFirstName());
//                cart.setShippingLastName(pojo.getShippingLastName());
//                cart.setShippingTown(pojo.getShippingTown());
//                cart.setShippingState(pojo.getShippingState());
//                cart.setShippingEmail(pojo.getShippingEmail());
//                cart.setShippingPhone(pojo.getShippingPhone());
//                cart.setShippingPostal(pojo.getShippingPostal());
//            }
//            cart.setNotes(pojo.getNotes());
//            cart.setStatus("Purchased");

            cartRepo.save(cart);
        }
        return "Saved Purchase";
    }

    @Override
    public String updateProduct(double quantity, Integer id) {
        productRepo.updateQuantity(quantity, id);
        return "Updated Quantity";
    }
}
