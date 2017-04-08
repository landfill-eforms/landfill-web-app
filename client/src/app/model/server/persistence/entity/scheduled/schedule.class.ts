import { SchedulePeriodBoundary } from '../../enums/schedule-period-boundary.enum';
import { ScheduleRecurrence } from '../../enums/schedule-recurrence.enum';

/**
 * This class was automatically generated from Schedule.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class Schedule {
	id:number;
	recurrence:ScheduleRecurrence;
	offset:number;
	periodBoundary:SchedulePeriodBoundary;
	frequency:number;
	active:boolean;
	lastOccurrence:number;
}