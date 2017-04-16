import { Instrument } from '../instrument/instrument.class';
import { WarmspotData } from './warmspot-data.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { User } from '../user/user.class';
import { ImeNumber } from './ime-number.class';

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
	imeNumbers:ImeNumber[];
	warmspotData:WarmspotData;
}