package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.repository.model.RankOrRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RRConverter implements AttributeConverter<RankOrRole, String> {

    @Override
    public String convertToDatabaseColumn(RankOrRole category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public RankOrRole convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(RankOrRole.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
