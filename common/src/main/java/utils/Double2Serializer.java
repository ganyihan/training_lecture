package utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class Double2Serializer extends JsonSerializer<Double> {
  private DecimalFormat df = new DecimalFormat("0.00");

  @Override
  public void serialize(Double data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
          throws IOException {
    if (data != null) {
      if (data == 0) {
        jsonGenerator.writeString("0");
      } else {
        jsonGenerator.writeString(df.format(data));
      }

    }
  }
}
