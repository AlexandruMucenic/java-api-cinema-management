package cinema.web.converter;

import cinema.core.model.BaseEntity;
import cinema.web.dto.BaseDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<Long>, Dto extends BaseDto>
        implements Converter<Model, Dto> {

    public List<Long> convertModelsToIDs(Collection<Model> models) {
        return models.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
    }

    public List<Long> convertDTOsToIDs(Collection<Dto> dtos) {
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toList());
    }

    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }
}
