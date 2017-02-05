import { IMENumber } from './ime-number.class';
import { User } from './user.class';
import { Instrument } from './instrument.class';
import { MonitoringPoint } from './monitoring-point.class';

/**
 * This class was automatically generated from InstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstantaneousData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	user:User;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
	imeNumber:IMENumber;
}