package cn.goldencis.vdp.core.constants;

import java.io.File;

public class ConstantsDto {
    public static final String MODEL_ID = "modelId";
    public static final String MODEL_NAME = "name";
    public static final String MODEL_REVISION = "revision";
    public static final String MODEL_DESCRIPTION = "description";

    public static final String FORM_TYPE_CUSTEM = "0";

    public static final Integer CONST_TRUE = 1;
    public static final Integer CONST_FALSE = 0;
    public static final Integer CONST_ERROR = -1;
    public static final Integer CONST_ERROR_USER_PASSWORD = 2;

    public static final String CONST_FORM_PROPERTY_TYPE = "formProperty";

    public static final String CONST_TASK_LISTENER_TYPE = "taskListener";

    public static final String ADMIN_NAME = "system";
    public static final String ADMIN_ID = "-1";

    public static final String MANUAL_CREATION_WORK_ORDER = "MANUAL_CREATION_WORK_ORDER";

    public static final String CHILD_TASK_DOING = "1";
    public static final String CHILD_TASK_COMPLETE = "2";
    public static final String CHILD_TASK_ERROR = "3";

    public static final String WORK_ORDER_PRIORITY_GENERAL = "1";
    public static final String WORK_ORDER_PRIORITY_URGENT = "2";

    public static final String WORK_ORDER_TYPE = "workType";

    public static final String SUPER_DEPARTMENT_ID = "1";
    public static final String DEPARTMENT_UNKOWN_GROUP = "2";

    public static final String WORK_ORDER_AUDITOR_PERSON = "WORK_ORDER_AUDITOR_PERSON";
    public static final String WORK_ORDER_PENDING = "1";
    public static final String WORK_ORDER_DOING = "2";
    public static final String WORK_ORDER_EXCEPTION = "3";
    public static final String WORK_ORDER_COMPLETE = "4";

    public static final String LIST_STR = "[]";

    public static final String REPLACE_HTML_TAG = "</?[^>]+>";

    public static final Integer NUMBER_FORMAT = 1;

    public static final String OPEN_PROCESS_STATUS_RUNNING = "1";
    public static final String OPEN_PROCESS_STATUS_STOP = "0";

    public static final Integer KNOWLEAGE_TYPE_DEFAULT = -1;

    public static final String DEFAULT_PERMISSION = "1";

    public static final String CANDIATE = "candidate";

    public static final String AUTH_FILE_NAME = "omsauthorized.auth";
    public static final String EXPORT_AUTH_FILE_NAME = "goldencis.oms";
    public static final Long LIMIT_TIME = 2 * 1000 * 6l;
    public static final Integer VALIDATE_FLAG = 1;

    public static final String NO_OUTGOING_SEQUENCE = "No outgoing sequence flow of the exclusive gateway";

    public static final String PROCJECT_IDENTIFICATION = "OMS";

    public static final String READ_OMS_AUTH_FILE_NAME = "readomsauth.out";

    public static final String LONG_TIME_LIMIT = "永久";

    public static final String APP_FILE_NAME = "oms.apk";

    public static final String APP_FILE_CATALOG = "/apk";

    public static final String APP_PACKAGE = "cn.goldencis.oms";

    public static final String APP_NAME = "OMS运维管理系统";

    public static final String SEC_CODE = "SEC_CODE";
    public static final String SEC_CODE_FLAG = "SEC_CODE_FLAG";
    public static final String SEC_CODE_PARAMETER = "sec_code_parameter";

    public static final String UPLOAD_PATH = File.separator + "upload" + File.separator + "attachment" + File.separator;
}
