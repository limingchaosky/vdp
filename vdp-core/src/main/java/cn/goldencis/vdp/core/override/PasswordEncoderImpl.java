package cn.goldencis.vdp.core.override;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import cn.goldencis.vdp.common.utils.SHACoderUtil;

/**
 * 重写加密实现，使用SHA2加密
 *
 * @author wangqn
 *
 * 2016年7月14日 上午10:54:41
 *
 */
@SuppressWarnings("deprecation")
public class PasswordEncoderImpl implements PasswordEncoder {
    private Logger log = Logger.getLogger(PasswordEncoderImpl.class);

    public String encodePassword(String password, Object salt) {
        try {
            log.debug("encodePassword: " + SHACoderUtil.encodeSHA256withSalt(password, salt));
            return SHACoderUtil.encodeSHA256withSalt(password, salt);
        } catch (Exception e) {
            log.error("encodePassword is error", e);
        }
        return password;
    }

    /**
     * 该方法不做密码匹配,改为userdetailServiceImpl做匹配
     * @param savePass
     * @param submitPass
     * @param salt
     * @return
     */
    public boolean isPasswordValid(String savePass, String submitPass, Object salt) {
        log.debug("savePass: " + savePass);
        /*String password = null;
        try {
            log.debug("submitPass: " + submitPass);
            password = SHACoderUtil.encodeSHA256withSalt(submitPass, salt);
            log.debug("password: " + password);
        } catch (Exception e) {
            log.error("isPasswordValid is error", e);
        }*/

        return true;
    }

}