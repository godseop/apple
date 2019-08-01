package org.godseop.apple.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.godseop.apple.entity.Dummy;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Intercepts(
    {
        //@Signature(type=StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
    }
)
public class QueryLogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //StatementHandler handler = (StatementHandler)invocation.getTarget();
        //Object param = handler.getParameterHandler().getParameterObject();

        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement)args[0];
        Object param = (Object)args[1];
        BoundSql boundSql = ms.getBoundSql(param);
        String sql = boundSql.getSql();



        System.out.println("====================================");
        System.out.println(invocation.getMethod().getName());
        System.out.println("====================================");
        System.out.println(ms.getId());
        System.out.println("====================================");
        System.out.println(boundSql.getSql());
        System.out.println("====================================");
        System.out.println(param);
        System.out.println("====================================");

        //log.error("SEQ : {}", param.getClass().getDeclaredMethod("seq", Integer.class).invoke(param, null).toString());
        log.error("SEQ : {}", ((Dummy) param).getSeq());
/*


        //MetaObject metaObject = MetaObject.forObject(handler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
        //MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");

        // 쿼리
        BoundSql boundSql = handler.getBoundSql();
        String sql = handler.getBoundSql().getSql();

        // 쿼리실행시 맵핑되는 파라메터들
        //String param = handler.getParameterHandler().getParameterObject()!=null ?
        //        handler.getParameterHandler().getParameterObject().toString() : "";

        Object param = handler.getParameterHandler().getParameterObject();

*/
        if (param == null) {
            sql = sql.replaceFirst("\\?", "''");
        } else {
            if (param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double) {
                sql = sql.replaceFirst("\\?", param.toString());
            } else if (param instanceof String) {
                sql = sql.replaceFirst("\\?", "'" + param + "'");
            } else if (param instanceof Map) {
                List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

                for (ParameterMapping mapping : parameterMappingList) {
                    String propValue = mapping.getProperty();
                    Object value = ((Map) param).get(propValue);

//                    if (StringUtils.equalsIgnoreCase("seq", propValue)) {
//                        id = value.toString();
//                        continue;
//                    }
                    log.error("PARAM : {} - {}", propValue, value.toString());
                    if (value instanceof String) {
                        sql = sql.replaceFirst("\\?", "'" + value + "'");
                    } else {
                        sql = sql.replaceFirst("\\?", value.toString());
                    }
                }
            } else {
                List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

                Class<?> paramClass = param.getClass();
                for (ParameterMapping mapping : parameterMappingList) {
                    String propValue = mapping.getProperty();

                    Field field = paramClass.getDeclaredField(propValue);
                    field.setAccessible(true);

//                    if (StringUtils.equalsIgnoreCase("seq", propValue)) {
//                        id = field.get(param).toString();
//                        continue;
//                    }
                    log.error("PARAM : {} - {}", propValue, field.get(param));
                    Class<?> javaType = mapping.getJavaType();

                    if (String.class == javaType) {
                        sql = sql.replaceFirst("\\?", "'" + field.get(param) + "'");
                    } else {
                        sql = sql.replaceFirst("\\?", field.get(param).toString());
                    }
                }
            }
        }

        // DB에다 로그 insert
        log.error("QUERY : {}", sql);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
