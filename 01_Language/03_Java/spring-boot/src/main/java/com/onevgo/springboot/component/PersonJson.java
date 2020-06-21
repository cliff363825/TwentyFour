package com.onevgo.springboot.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.onevgo.springboot.bean.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@JsonComponent
public class PersonJson {
    public static class Serializer extends JsonSerializer<Person> {

        @Override
        public void serialize(Person value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            Map<String, Object> map = new HashMap<>();
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(Person.class);
            for (PropertyDescriptor pd : propertyDescriptors) {
                Method readMethod = pd.getReadMethod();
                if (Modifier.isPublic(readMethod.getModifiers())) {
                    try {
                        map.put(pd.getName(), readMethod.invoke(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            gen.writeObject(map);
        }
    }

    public static class Deserializer extends JsonDeserializer<Person> {

        @Override
        public Person deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return p.readValueAs(Person.class);
        }
    }
}
