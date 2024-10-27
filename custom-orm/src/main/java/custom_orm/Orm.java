package custom_orm;

import custom_orm.annotations.Column;
import custom_orm.annotations.Entity;
import custom_orm.annotations.Id;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public class Orm {

    private DataSource dataSource;

    public Orm(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T findById(Class<T> type, Object id) {
        String tableName = type.getAnnotation(Entity.class).name();
        Field idField = Arrays.stream(type.getDeclaredFields())
                .filter(a -> a.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Id field is not specified"));

        String query = "SELECT * FROM " + tableName + " WHERE " + idField.getName() + " = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                T entity = type.getConstructor().newInstance();
                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(getColumnName(field));
                    field.set(entity, value);
                }
                return entity;
            }

        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Entity not found");
    }

    private String getColumnName(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            return field.getAnnotation(Column.class).name();
        } else {
            return CaseUtil.camelToSnake(field.getName());
        }
    }
}
