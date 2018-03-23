package com.courses.tellus.autosalon.dao;

import com.courses.tellus.autosalon.model.Autosalon;

import java.util.List;

public interface AutosalonDaoEntyty extends AutosalonDaoInterface<Autosalon> {

    List<Autosalon> findPersonsByLastName(String firstName);
}
