import { Instrument } from '../instrument/instrument.class';
import { IMENumber } from './ime-number.class';
import { User } from '../user/user.class';
import { MonitoringPoint } from '../../../model/monitoring-point.enum';

/**
 * This class was automatically generated from InstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstantaneousData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	inspector:User;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
	imeNumber:IMENumber;
}