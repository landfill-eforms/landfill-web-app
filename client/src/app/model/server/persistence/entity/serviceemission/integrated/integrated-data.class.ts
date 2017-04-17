import { MonitoringPoint } from '../../../enums/location/monitoring-point.enum';
import { Instrument } from '../../instrument/instrument.class';
import { User } from '../../user/user.class';

/**
 * This class was automatically generated from IntegratedData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IntegratedData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	inspector:User;
	bagNumber:number;
	volume:number;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
}