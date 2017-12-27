package cn.goldencis.vdp;

import org.junit.Test;

import java.util.UUID;

/**
 * Created by mengll on 2017/12/22.
 */
public class UUIDTest {

    @Test
    public void uuidTest(){

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(uuid.toString().length());

    }

}
