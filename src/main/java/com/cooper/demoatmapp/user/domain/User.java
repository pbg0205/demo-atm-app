package com.cooper.demoatmapp.user.domain;

import com.cooper.demoatmapp.user.converter.UserAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Setter
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false, length = 20)
    @Convert(converter = UserAttributeConverter.class)
    private String name; //사용자 이름

    @Column(nullable = false, length = 20)
    @Convert(converter = UserAttributeConverter.class)
    private String username; // 사용자 아이디

    @Column(name = "phone_number", nullable = false, length = 30)
    @Convert(converter = UserAttributeConverter.class)
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    @Convert(converter = UserAttributeConverter.class)
    private String email;

    private User(String name, String username, String phoneNumber, String email) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static User create(String name, String username,String phoneNumber, String email) {
        return new User(name, username, phoneNumber, email);
    }

}
