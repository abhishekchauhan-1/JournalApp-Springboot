package com.zomiles.JournalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Document //Annotation is used for creating a document in MongoDB
@Data //Lombok DAta Annotation is used for creating getter setter constructor automatically compile time
@NoArgsConstructor
public class  JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

}
