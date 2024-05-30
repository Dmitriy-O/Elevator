package org.sytoss.convertor;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sytoss.bom.Address;
import org.sytoss.bom.Building;
import org.sytoss.bom.Cabin;
import org.sytoss.bom.Engine;
import org.sytoss.bom.Lift;
import org.sytoss.dto.AddressDTO;
import org.sytoss.dto.BuildingDTO;
import org.sytoss.dto.CabinDTO;
import org.sytoss.dto.EngineDTO;
import org.sytoss.dto.LiftDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-29T12:27:21+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class BuildingConvertorImpl implements BuildingConvertor {

    @Autowired
    private LiftConvertor liftConvertor;

    @Override
    public BuildingDTO toDTO(Building building) {
        if ( building == null ) {
            return null;
        }

        BuildingDTO buildingDTO = new BuildingDTO();

        buildingDTO.setAddress( addressToAddressDTO( building.getAddress() ) );
        buildingDTO.setBuildingNumber( buildingAddressBuildingNumber( building ) );
        buildingDTO.setLifts( liftListToLiftDTOList( building.getLifts() ) );
        if ( building.getId() != null ) {
            buildingDTO.setId( building.getId() );
        }
        buildingDTO.setFloorCount( building.getFloorCount() );

        return buildingDTO;
    }

    @Override
    public Building fromDTO(BuildingDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Building building = new Building();

        if ( dto.getAddress() != null ) {
            if ( building.getAddress() == null ) {
                building.setAddress( new Address() );
            }
            addressDTOToAddress( dto.getAddress(), building.getAddress() );
        }
        if ( building.getAddress() == null ) {
            building.setAddress( new Address() );
        }
        buildingDTOToAddress( dto, building.getAddress() );
        if ( building.getLifts() != null ) {
            List<Lift> list = liftDTOListToLiftList( dto.getLifts() );
            if ( list != null ) {
                building.getLifts().addAll( list );
            }
        }
        building.setId( dto.getId() );
        building.setFloorCount( dto.getFloorCount() );

        return building;
    }

    protected AddressDTO addressToAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setCity( address.getCity() );
        addressDTO.setStreet( address.getStreet() );

        return addressDTO;
    }

    private int buildingAddressBuildingNumber(Building building) {
        if ( building == null ) {
            return 0;
        }
        Address address = building.getAddress();
        if ( address == null ) {
            return 0;
        }
        int buildingNumber = address.getBuildingNumber();
        return buildingNumber;
    }

    protected CabinDTO cabinToCabinDTO(Cabin cabin) {
        if ( cabin == null ) {
            return null;
        }

        CabinDTO cabinDTO = new CabinDTO();

        cabinDTO.setId( cabin.getId() );
        cabinDTO.setType( cabin.getType() );

        return cabinDTO;
    }

    protected EngineDTO engineToEngineDTO(Engine engine) {
        if ( engine == null ) {
            return null;
        }

        EngineDTO engineDTO = new EngineDTO();

        engineDTO.setId( engine.getId() );
        engineDTO.setType( engine.getType() );
        if ( engine.getStatus() != null ) {
            engineDTO.setStatus( engine.getStatus().name() );
        }

        return engineDTO;
    }

    protected LiftDTO liftToLiftDTO(Lift lift) {
        if ( lift == null ) {
            return null;
        }

        LiftDTO liftDTO = new LiftDTO();

        liftDTO.setId( lift.getId() );
        liftDTO.setCabinPosition( lift.getCabinPosition() );
        if ( lift.getButtonTemplate() != null ) {
            liftDTO.setButtonTemplate( lift.getButtonTemplate().name() );
        }
        if ( lift.getStatus() != null ) {
            liftDTO.setStatus( lift.getStatus().name() );
        }
        liftDTO.setCabin( cabinToCabinDTO( lift.getCabin() ) );
        liftDTO.setEngine( engineToEngineDTO( lift.getEngine() ) );
        liftDTO.setFloorNumbers( liftConvertor.map( lift.getFloorNumbers() ) );

        return liftDTO;
    }

    protected List<LiftDTO> liftListToLiftDTOList(List<Lift> list) {
        if ( list == null ) {
            return null;
        }

        List<LiftDTO> list1 = new ArrayList<LiftDTO>( list.size() );
        for ( Lift lift : list ) {
            list1.add( liftToLiftDTO( lift ) );
        }

        return list1;
    }

    protected void addressDTOToAddress(AddressDTO addressDTO, Address mappingTarget) {
        if ( addressDTO == null ) {
            return;
        }

        mappingTarget.setCity( addressDTO.getCity() );
        mappingTarget.setStreet( addressDTO.getStreet() );
    }

    protected void buildingDTOToAddress(BuildingDTO buildingDTO, Address mappingTarget) {
        if ( buildingDTO == null ) {
            return;
        }

        mappingTarget.setBuildingNumber( buildingDTO.getBuildingNumber() );
    }

    protected List<Lift> liftDTOListToLiftList(List<LiftDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Lift> list1 = new ArrayList<Lift>( list.size() );
        for ( LiftDTO liftDTO : list ) {
            list1.add( liftConvertor.fromDTO( liftDTO ) );
        }

        return list1;
    }
}
