package com.zouchaoqun.login.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zouchaoqun.login.enums.ErrorEnum;
import com.zouchaoqun.login.exception.BizException;
import com.zouchaoqun.login.util.AESUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zouchaoqun on 2020/9/13.
 */
@Log4j2
public class SlefHikarDataSource extends HikariDataSource {

    private String encryptPasswd;

    private String PUBLICKEY = "";

    @Override
    public String getPassword(){

        if(StringUtils.isNotBlank(encryptPasswd)){
            return encryptPasswd;
        }
        String encPassword = super.getPassword();

        log.info("数据库密码加解密，{"+encPassword+"}");
        try{
            String key = AESUtil.generateKey(256, "UTF-8");
            String initVector = key.substring(0, 16);
            String encType = "AES/CBC/PKCS5PADDING";
            //  密文解密，解密方法可以修改
            encryptPasswd = AESUtil.decrypt(encryptPasswd, key, initVector, encType);

            return encryptPasswd;
        }catch (Exception e){
            log.error("数据库密码解密出错，{"+encPassword+"}");
            log.error(e.getCause(), e);
            // "数据库密码解密失败"
            throw new BizException(ErrorEnum.DATASOURCE_DECRYPT_ERROR, e);
        }
    }





}
