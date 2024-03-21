package com.rahul.SQL.DAO.mapper;

public interface Mapper<A,B> {

    B mapTo(A a);
    A mapFrom(B b);

}
