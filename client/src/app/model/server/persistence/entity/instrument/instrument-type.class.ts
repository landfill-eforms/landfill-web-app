import { Instrument } from './instrument.class';

/**
 * This class was automatically generated from InstrumentType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstrumentType {
	id:number;
	type:string;
	manufacturer:string;
	description:string;
	instantaneous:boolean;
	probe:boolean;
	methanePercent:boolean;
	methanePpm:boolean;
	hydrogenSulfidePpm:boolean;
	oxygenPercent:boolean;
	carbonDioxidePercent:boolean;
	nitrogenPercent:boolean;
	pressure:boolean;
	instruments:Instrument[];
}