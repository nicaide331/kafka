package com.zr.kafka.produce.serializer;

import java.util.Objects;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 17:05:00
 */
public class User {
    private String firstName;

    private String lastName;

    private int age;

    private String address;

    public User(String firstName, String lastName, int age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getAge() == user.getAge() &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getAddress().equals(user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAge(), getAddress());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
