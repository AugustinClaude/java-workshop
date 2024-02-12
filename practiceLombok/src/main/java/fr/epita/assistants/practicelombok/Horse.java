package fr.epita.assistants.practicelombok;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"name","nickname"})
@ToString
public class Horse {
    @Setter private String name;
    @Setter @ToString.Exclude private String nickname;
    private int speed;
}
