package edu.ouc.it.mapper;

import edu.ouc.it.domain.TestUser;

import java.util.List;

/**
 * TestUser
 *
 * @author skyUnv
 * created on 2018/11/4 10:01
 */
public interface TestUserMapper {
    void add(TestUser u);
    void delete_by_id(Long id);
    TestUser get_by_id(Long id);
    void update(TestUser u);
    List<TestUser> table_list();
}
