import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { User } from '../user/user.class';
import { Instrument } from '../instrument/instrument.class';

/**
 * This class was automatically generated from SurfaceEmissionData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class SurfaceEmissionData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	inspector:User;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
}