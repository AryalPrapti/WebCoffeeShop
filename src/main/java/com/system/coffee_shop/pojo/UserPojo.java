package com.system.coffee_shop.pojo;

import com.system.coffee_shop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private Integer id;

    private String fullName;

    private String email;

    private String phone;
    private String password;

    // 14/02/2023
    private String image;
    //.......

    public UserPojo(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.password = user.getPassword();
        this.image = user.getImage();
    }

//    public UserPojo(String username, String image) {
//        this.username = username;
//        this.image = image;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getImage() {
//        return image;
//    }
}
