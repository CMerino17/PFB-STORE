package com.kreitek.store.application.mapper;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.domain.entity.Category;
import com.kreitek.store.domain.entity.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T17:55:25+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Item> toEntity(List<ItemDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Item> list = new ArrayList<Item>( dtoList.size() );
        for ( ItemDTO itemDTO : dtoList ) {
            list.add( toEntity( itemDTO ) );
        }

        return list;
    }

    @Override
    public List<ItemDTO> toDto(List<Item> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ItemDTO> list = new ArrayList<ItemDTO>( entityList.size() );
        for ( Item item : entityList ) {
            list.add( toDto( item ) );
        }

        return list;
    }

    @Override
    public Item toEntity(ItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Item item = new Item();

        item.setCategory( categoryMapper.fromId( dto.getCategoryId() ) );
        item.setId( dto.getId() );
        item.setName( dto.getName() );
        item.setDescription( dto.getDescription() );
        item.setPrice( dto.getPrice() );
        byte[] image = dto.getImage();
        if ( image != null ) {
            item.setImage( Arrays.copyOf( image, image.length ) );
        }

        return item;
    }

    @Override
    public ItemDTO toDto(Item entity) {
        if ( entity == null ) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setCategoryId( entityCategoryId( entity ) );
        itemDTO.setCategoryName( entityCategoryName( entity ) );
        itemDTO.setId( entity.getId() );
        itemDTO.setName( entity.getName() );
        itemDTO.setDescription( entity.getDescription() );
        itemDTO.setPrice( entity.getPrice() );
        byte[] image = entity.getImage();
        if ( image != null ) {
            itemDTO.setImage( Arrays.copyOf( image, image.length ) );
        }

        return itemDTO;
    }

    private Long entityCategoryId(Item item) {
        if ( item == null ) {
            return null;
        }
        Category category = item.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCategoryName(Item item) {
        if ( item == null ) {
            return null;
        }
        Category category = item.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
