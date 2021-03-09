package com.huasit.apm.core.user.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User>  {

    /**
     *
     */
    @Query("from User where id=?1")
    User findUserById(Long id);

    /**
     *
     */
    @Query("from User where state=0 and username=?1")
    User findByUsername(String username);

    /**
     *
     */
    @Query("from User where state=0 and username=?1")
    User findLoginUserByUsername(String username);

    /**
     *
     */
    @Query("from User where state=0 and username=?1 and password=?2")
    User findLoginUserByUsernameAndPassword(String username, String password);

    /**
     *
     */
    @Query("from User where state=0 and id in (select userId from UserToken where enable=true and token=?1)")
    User findLoginUserByToken(String token);
    /**
     *
     */
    @Modifying
    @Query("update User set password=:password,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updatePassword(@Param("id") Long id, @Param("password") String password, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Modifying
    @Query("update User set state=:state,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateState(@Param("id") Long id, @Param("state") User.State state, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Query("from User where state=0 and thirdparty.id=?1")
    List<User> findByThirdpartyId(Long thirdpartyId);

    /**
     *
     */
    @Query(value="select a.user_code,a.dept_code,b.type,b.name from user_dept a left join dept b on a.dept_code=b.code",nativeQuery = true)
    List<Object[]> findUserDept();
}