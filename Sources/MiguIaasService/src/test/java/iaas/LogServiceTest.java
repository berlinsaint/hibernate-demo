/**
 * Copyright (C), 科大讯飞股份有限公司
 * FileName: LogServiceTest
 */
package iaas;

import com.alibaba.fastjson.JSON;
import com.iflytek.iaas.Application;
import com.iflytek.iaas.consts.K8sAPPType;
import com.iflytek.iaas.consts.LogType;
import com.iflytek.iaas.dto.OperationLogDTO;
import com.iflytek.iaas.dto.k8s.ServiceConfigDTO;
import com.iflytek.iaas.service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * 〈日志服务单元测试〉
 *
 * @author xwliu
 * @create 2018/4/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    public void saveOperationLogTest(){
        ServiceConfigDTO serviceConfigDTO = new ServiceConfigDTO();
        serviceConfigDTO.setImgDeployNames(Arrays.asList("mysql-5-7"));
        serviceConfigDTO.setNamespace("test2");
        serviceConfigDTO.setServiceName("test-service");
        serviceConfigDTO.setType(K8sAPPType.EXTERNAL_SERVICE);
        serviceConfigDTO.setPodPort(36);

        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setCreator("test");
        operationLogDTO.setType(LogType.NEW_DEPLOY);
        operationLogDTO.setObj("mysql-5-7");
        operationLogDTO.setDetail("新建部署");
        operationLogDTO.setParam(JSON.toJSONString(serviceConfigDTO));
        for(int i=0; i<10; i++){
            logService.saveOperationLog(operationLogDTO);
        }

    }
    @Test
    public void findOperationLogByTypeAndCreatorTest(){
        Integer pageIndex=1;
        Integer pageSize=6;
        logService.findOperationLogByTypeAndCreator(null,null,pageIndex,pageSize);
    }


}
