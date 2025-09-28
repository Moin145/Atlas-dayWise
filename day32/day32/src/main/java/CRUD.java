import software.amazon.awssdk.services.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.model.*;
import java.util.*;

public class CRUD {
    public static void main(String[] args) {
        DynamoDbClient ddb = DynamoDbClient.create();
        String tableName = "Users";

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s("101").build());
        item.put("name", AttributeValue.builder().s("John").build());
        ddb.putItem(PutItemRequest.builder().tableName(tableName).item(item).build());
        System.out.println("Created: " + item);

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s("101").build());
        Map<String, AttributeValue> returned = ddb.getItem(GetItemRequest.builder().tableName(tableName).key(key).build()).item();
        System.out.println("Read: " + returned);

        ddb.updateItem(UpdateItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .updateExpression("SET name = :n")
                .expressionAttributeValues(Map.of(":n", AttributeValue.builder().s("Mike").build()))
                .build());
        Map<String, AttributeValue> updated = ddb.getItem(GetItemRequest.builder().tableName(tableName).key(key).build()).item();
        System.out.println("Updated: " + updated);

        ddb.deleteItem(DeleteItemRequest.builder().tableName(tableName).key(key).build());
        Map<String, AttributeValue> deleted = ddb.getItem(GetItemRequest.builder().tableName(tableName).key(key).build()).item();
        System.out.println("Deleted: " + deleted);

        ddb.close();
    }
}
