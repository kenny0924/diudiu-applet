package com.diudiu.applet.mapper;

import com.diudiu.applet.entity.JwtToken;
import org.apache.ibatis.annotations.*;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/28/17
 * @since 0.1
 */
public interface JwtMapper {

    /**
     * 查询用户是否已经有Token
     *
     * @param userId
     * @author Zhibin Liu
     * @time 12/29/17 13:51
     */
    @Select("SELECT\n" +
            "            T.TOKEN_UUID,\n" +
            "            T.TOKEN,\n" +
            "            T.USER_ID,\n" +
            "            T.MODIFY_TIME\n" +
            "        FROM USER_AUTH_TOKEN T\n" +
            "        WHERE T.USER_ID = #{userId,       javaType=long}")
    JwtToken queryJwtTokenByUserUuid(@Param("userId") Long userId);

    /**
     * 新增 Token
     *
     * @param jwtToken
     * @author Zhibin Liu
     * @time 12/28/17 18:06
     */
    @Insert("INSERT INTO USER_AUTH_TOKEN\n" +
            "        (\n" +
            "        TOKEN_UUID,\n" +
            "        USER_ID,\n" +
            "        TOKEN,\n" +
            "        CREATED_TIME\n" +
            "        )\n" +
            "        VALUES (\n" +
            "        #{tokenUuid,        javaType=string},\n" +
            "        #{userId,           javaType=long},\n" +
            "        #{token,            javaType=string},\n" +
            "        NOW()\n" +
            "        )")
    Integer doCreateJwt(JwtToken jwtToken);

    /**
     * 根据TokenUuid删除Token
     *
     * @param uuid
     * @author Zhibin Liu
     * @time 12/28/17 18:18
     */
    @Delete("DELETE FROM USER_AUTH_TOKEN\n" +
            "          WHERE TOKEN_UUID = #{tokenUuid,     javaType=string}")
    Integer doDeleteJwtByTokenUuid(@Param("tokenUuid") String uuid);

    /**
     * 修改Token
     *
     * @param jwtToken
     * @author Zhibin Liu
     * @time 12/29/17 13:55
     */
    @Update("UPDATE USER_AUTH_TOKEN T\n" +
            "            SET T.TOKEN       = #{token,          javaType=string},\n" +
            "                T.TOKEN_UUID  = #{tokenUuid,      javaType=string}\n" +
            "        WHERE T.USER_ID       = #{userId,       javaType=long}")
    Integer doModifyJwtByUserUuid(JwtToken jwtToken);
}
