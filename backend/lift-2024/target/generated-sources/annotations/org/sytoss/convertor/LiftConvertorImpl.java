package org.sytoss.convertor;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sytoss.bom.Cabin;
import org.sytoss.bom.Door;
import org.sytoss.bom.DoorStatus;
import org.sytoss.bom.Engine;
import org.sytoss.bom.EngineStatus;
import org.sytoss.bom.FloorButtonTemplate;
import org.sytoss.bom.Lift;
import org.sytoss.bom.LiftStatus;
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
public class LiftConvertorImpl implements LiftConvertor {

    @Autowired
    private LiftConverterHelper liftConverterHelper;

    @Override
    public LiftDTO toDto(Lift lift, Integer buildingId) {
        if ( lift == null && buildingId == null ) {
            return null;
        }

        LiftDTO liftDTO = new LiftDTO();

        if ( lift != null ) {
            liftDTO.setCabin( cabinToCabinDTO( lift.getCabin() ) );
            liftDTO.setEngine( engineToEngineDTO( lift.getEngine() ) );
            liftDTO.setId( lift.getId() );
            liftDTO.setCabinPosition( lift.getCabinPosition() );
            if ( lift.getButtonTemplate() != null ) {
                liftDTO.setButtonTemplate( lift.getButtonTemplate().name() );
            }
            if ( lift.getStatus() != null ) {
                liftDTO.setStatus( lift.getStatus().name() );
            }
            liftDTO.setFloorNumbers( map( lift.getFloorNumbers() ) );
        }
        liftDTO.setBuilding( liftConverterHelper.findById( buildingId ) );

        return liftDTO;
    }

    @Override
    public Lift fromDTO(LiftDTO liftDTO) {
        if ( liftDTO == null ) {
            return null;
        }

        Lift lift = new Lift();

        lift.setCabin( cabinDTOToCabin( liftDTO.getCabin() ) );
        lift.setEngine( engineDTOToEngine( liftDTO.getEngine() ) );
        lift.setBuildingId( liftDTOBuildingId( liftDTO ) );
        lift.setCabinPosition( liftDTO.getCabinPosition() );
        lift.setId( liftDTO.getId() );
        if ( liftDTO.getStatus() != null ) {
            lift.setStatus( Enum.valueOf( LiftStatus.class, liftDTO.getStatus() ) );
        }
        if ( liftDTO.getButtonTemplate() != null ) {
            lift.setButtonTemplate( Enum.valueOf( FloorButtonTemplate.class, liftDTO.getButtonTemplate() ) );
        }
        lift.setFloorNumbers( map( liftDTO.getFloorNumbers() ) );

        return lift;
    }

    private DoorStatus cabinDoorDoorStatus(Cabin cabin) {
        if ( cabin == null ) {
            return null;
        }
        Door door = cabin.getDoor();
        if ( door == null ) {
            return null;
        }
        DoorStatus doorStatus = door.getDoorStatus();
        if ( doorStatus == null ) {
            return null;
        }
        return doorStatus;
    }

    protected CabinDTO cabinToCabinDTO(Cabin cabin) {
        if ( cabin == null ) {
            return null;
        }

        CabinDTO cabinDTO = new CabinDTO();

        cabinDTO.setId( cabin.getSerialNumber() );
        DoorStatus doorStatus = cabinDoorDoorStatus( cabin );
        if ( doorStatus != null ) {
            cabinDTO.setDoorStatus( doorStatus.name() );
        }
        cabinDTO.setType( cabin.getType() );

        return cabinDTO;
    }

    protected EngineDTO engineToEngineDTO(Engine engine) {
        if ( engine == null ) {
            return null;
        }

        EngineDTO engineDTO = new EngineDTO();

        engineDTO.setId( engine.getSerialNumber() );
        if ( engine.getStatus() != null ) {
            engineDTO.setStatus( engine.getStatus().name() );
        }
        engineDTO.setType( engine.getType() );

        return engineDTO;
    }

    protected Door cabinDTOToDoor(CabinDTO cabinDTO) {
        if ( cabinDTO == null ) {
            return null;
        }

        Door door = new Door();

        if ( cabinDTO.getDoorStatus() != null ) {
            door.setDoorStatus( Enum.valueOf( DoorStatus.class, cabinDTO.getDoorStatus() ) );
        }

        return door;
    }

    protected Cabin cabinDTOToCabin(CabinDTO cabinDTO) {
        if ( cabinDTO == null ) {
            return null;
        }

        Cabin cabin = new Cabin();

        cabin.setDoor( cabinDTOToDoor( cabinDTO ) );
        cabin.setId( cabinDTO.getId() );
        cabin.setType( cabinDTO.getType() );

        return cabin;
    }

    protected Engine engineDTOToEngine(EngineDTO engineDTO) {
        if ( engineDTO == null ) {
            return null;
        }

        Engine engine = new Engine();

        engine.setId( engineDTO.getId() );
        engine.setType( engineDTO.getType() );
        if ( engineDTO.getStatus() != null ) {
            engine.setStatus( Enum.valueOf( EngineStatus.class, engineDTO.getStatus() ) );
        }

        return engine;
    }

    private int liftDTOBuildingId(LiftDTO liftDTO) {
        if ( liftDTO == null ) {
            return 0;
        }
        BuildingDTO building = liftDTO.getBuilding();
        if ( building == null ) {
            return 0;
        }
        int id = building.getId();
        return id;
    }
}
