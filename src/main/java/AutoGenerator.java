import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author WangBo
 * @since 2024/3/26 10:47
 */
public class AutoGenerator {

    private final static String URL = "jdbc:mysql://127.0.0.1/test?useUnicode=true&allowMultiQueries=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true&serverTimezone=Asia/Shanghai&useSSL=false";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";
    private final static String[] TABLE_NAMES = new String[]{"t_user"};

    /**
     * 表名的前缀,从表生成代码时会去掉前缀，没有前缀就只写_
     **/
    private static final String TABLE_PREFIX = "t_";

    //父包名
    private static final String PARENT_PACKAGE = "com.xxx.test";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("WangBo")
                            .disableOpenDir()//是否打开输出目录
                            //启用swagger
                            .enableSwagger()
                            //指定输出目录
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");
                    //                            .outputDir("C:\\CodeGenerator");
                })
                .packageConfig(builder -> {
                    builder.parent(PARENT_PACKAGE)//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                            .entity("entity")//实体类包名
                            .controller("controller")//控制层包名
                            .mapper("mapper")//mapper层包名
                            //.other("dto")//生成dto目录 可不用
                            .service("service")//service层包名
                            .serviceImpl("service.impl");//service实现类包名
                    //自定义mapper.xml文件输出目录
                    //                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    //设置要生成的表名
                    builder.addInclude(TABLE_NAMES)
                            .addTablePrefix(TABLE_PREFIX)//设置表前缀过滤
                            // entity配置
                            .entityBuilder()
                            .javaTemplate("/templates/entity.java.vm")
                            .enableLombok()//开启lombok模式
                            .enableChainModel()//开启链式模式
                            .naming(NamingStrategy.underline_to_camel)//数据表映射实体命名策略：默认下划线转驼峰underline_to_camel
                            .columnNaming(NamingStrategy.underline_to_camel)//表字段映射实体属性命名规则：默认null，不指定按照naming执行
                            .idType(IdType.ASSIGN_ID)//添加全局主键类型
                            .formatFileName("%s")//格式化实体名称，%s取消首字母I,
                            // mapper配置
                            .mapperBuilder()
                            .mapperTemplate("/templates/mapper.java.vm")
                            //                            .enableMapperAnnotation()//开启mapper注解
                            .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                            .enableBaseColumnList()//启用xml文件中的BaseColumnList
                            .formatMapperFileName("%sMapper")//格式化Dao类名称
                            .formatXmlFileName("%sMapper")//格式化xml文件名称
                            // service配置
                            .serviceBuilder()
                            .serviceTemplate("/templates/service.java.vm")
                            .serviceImplTemplate("/templates/serviceImpl.java.vm")
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
                            // controller配置
                            .controllerBuilder()
                            .template("/templates/controller.java.vm")
                            .enableRestStyle();
                })
                .execute();
    }
}
