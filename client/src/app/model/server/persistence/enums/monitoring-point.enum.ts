import { MonitoringPointType } from './monitoring-point-type.enum';
import { Site } from './site.enum';

/**
 * This enum was automatically generated from MonitoringPoint.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class MonitoringPoint {

	static readonly LC_AMBIENT_1A:MonitoringPoint = new MonitoringPoint(0, "LC_AMBIENT_1A", "1A", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_2B:MonitoringPoint = new MonitoringPoint(1, "LC_AMBIENT_2B", "2B", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_3B:MonitoringPoint = new MonitoringPoint(2, "LC_AMBIENT_3B", "3B", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_4A:MonitoringPoint = new MonitoringPoint(3, "LC_AMBIENT_4A", "4A", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_4B:MonitoringPoint = new MonitoringPoint(4, "LC_AMBIENT_4B", "4B", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_5A:MonitoringPoint = new MonitoringPoint(5, "LC_AMBIENT_5A", "5A", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_5B:MonitoringPoint = new MonitoringPoint(6, "LC_AMBIENT_5B", "5B", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_6A:MonitoringPoint = new MonitoringPoint(7, "LC_AMBIENT_6A", "6A", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly LC_AMBIENT_6B:MonitoringPoint = new MonitoringPoint(8, "LC_AMBIENT_6B", "6B", Site.LOPEZ, MonitoringPointType.AMBIENT);
	static readonly SH_AMBIENT_01N:MonitoringPoint = new MonitoringPoint(9, "SH_AMBIENT_01N", "01N", Site.SHELDON, MonitoringPointType.AMBIENT);
	static readonly SH_AMBIENT_02S:MonitoringPoint = new MonitoringPoint(10, "SH_AMBIENT_02S", "02S", Site.SHELDON, MonitoringPointType.AMBIENT);
	static readonly TC_AMBIENT_SB:MonitoringPoint = new MonitoringPoint(11, "TC_AMBIENT_SB", "SB", Site.TOYON, MonitoringPointType.AMBIENT);
	static readonly TC_AMBIENT_ST:MonitoringPoint = new MonitoringPoint(12, "TC_AMBIENT_ST", "ST", Site.TOYON, MonitoringPointType.AMBIENT);
	static readonly GP_BIOFILTER_A_IN:MonitoringPoint = new MonitoringPoint(13, "GP_BIOFILTER_A_IN", "A-In", Site.GRIFFITH_PARK, MonitoringPointType.BIOFILTER);
	static readonly GP_BIOFILTER_A_OUT:MonitoringPoint = new MonitoringPoint(14, "GP_BIOFILTER_A_OUT", "A-Out", Site.GRIFFITH_PARK, MonitoringPointType.BIOFILTER);
	static readonly GP_BIOFILTER_B_IN:MonitoringPoint = new MonitoringPoint(15, "GP_BIOFILTER_B_IN", "B-In", Site.GRIFFITH_PARK, MonitoringPointType.BIOFILTER);
	static readonly GP_BIOFILTER_B_OUT:MonitoringPoint = new MonitoringPoint(16, "GP_BIOFILTER_B_OUT", "B-Out", Site.GRIFFITH_PARK, MonitoringPointType.BIOFILTER);
	static readonly BC_GRID_A1:MonitoringPoint = new MonitoringPoint(17, "BC_GRID_A1", "A1", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A2:MonitoringPoint = new MonitoringPoint(18, "BC_GRID_A2", "A2", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A3:MonitoringPoint = new MonitoringPoint(19, "BC_GRID_A3", "A3", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A4:MonitoringPoint = new MonitoringPoint(20, "BC_GRID_A4", "A4", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A5:MonitoringPoint = new MonitoringPoint(21, "BC_GRID_A5", "A5", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A6:MonitoringPoint = new MonitoringPoint(22, "BC_GRID_A6", "A6", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A7:MonitoringPoint = new MonitoringPoint(23, "BC_GRID_A7", "A7", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A8:MonitoringPoint = new MonitoringPoint(24, "BC_GRID_A8", "A8", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A9:MonitoringPoint = new MonitoringPoint(25, "BC_GRID_A9", "A9", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A10:MonitoringPoint = new MonitoringPoint(26, "BC_GRID_A10", "A10", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A11:MonitoringPoint = new MonitoringPoint(27, "BC_GRID_A11", "A11", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_A12:MonitoringPoint = new MonitoringPoint(28, "BC_GRID_A12", "A12", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_B1:MonitoringPoint = new MonitoringPoint(29, "BC_GRID_B1", "B1", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_B2:MonitoringPoint = new MonitoringPoint(30, "BC_GRID_B2", "B2", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_B3:MonitoringPoint = new MonitoringPoint(31, "BC_GRID_B3", "B3", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly BC_GRID_B4:MonitoringPoint = new MonitoringPoint(32, "BC_GRID_B4", "B4", Site.BISHOPS, MonitoringPointType.GRID);
	static readonly GA_GRID_1:MonitoringPoint = new MonitoringPoint(33, "GA_GRID_1", "1", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_2:MonitoringPoint = new MonitoringPoint(34, "GA_GRID_2", "2", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_3:MonitoringPoint = new MonitoringPoint(35, "GA_GRID_3", "3", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_4:MonitoringPoint = new MonitoringPoint(36, "GA_GRID_4", "4", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_5:MonitoringPoint = new MonitoringPoint(37, "GA_GRID_5", "5", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_6:MonitoringPoint = new MonitoringPoint(38, "GA_GRID_6", "6", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_7:MonitoringPoint = new MonitoringPoint(39, "GA_GRID_7", "7", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_8:MonitoringPoint = new MonitoringPoint(40, "GA_GRID_8", "8", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_9:MonitoringPoint = new MonitoringPoint(41, "GA_GRID_9", "9", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_10:MonitoringPoint = new MonitoringPoint(42, "GA_GRID_10", "10", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_11:MonitoringPoint = new MonitoringPoint(43, "GA_GRID_11", "11", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly GA_GRID_12:MonitoringPoint = new MonitoringPoint(44, "GA_GRID_12", "12", Site.GAFFEY, MonitoringPointType.GRID);
	static readonly LC_GRID_1:MonitoringPoint = new MonitoringPoint(45, "LC_GRID_1", "1", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_2:MonitoringPoint = new MonitoringPoint(46, "LC_GRID_2", "2", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_3:MonitoringPoint = new MonitoringPoint(47, "LC_GRID_3", "3", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_4:MonitoringPoint = new MonitoringPoint(48, "LC_GRID_4", "4", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_5:MonitoringPoint = new MonitoringPoint(49, "LC_GRID_5", "5", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_6:MonitoringPoint = new MonitoringPoint(50, "LC_GRID_6", "6", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_7:MonitoringPoint = new MonitoringPoint(51, "LC_GRID_7", "7", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_8:MonitoringPoint = new MonitoringPoint(52, "LC_GRID_8", "8", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_9:MonitoringPoint = new MonitoringPoint(53, "LC_GRID_9", "9", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_10:MonitoringPoint = new MonitoringPoint(54, "LC_GRID_10", "10", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_11:MonitoringPoint = new MonitoringPoint(55, "LC_GRID_11", "11", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_12:MonitoringPoint = new MonitoringPoint(56, "LC_GRID_12", "12", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_13:MonitoringPoint = new MonitoringPoint(57, "LC_GRID_13", "13", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_14:MonitoringPoint = new MonitoringPoint(58, "LC_GRID_14", "14", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_15:MonitoringPoint = new MonitoringPoint(59, "LC_GRID_15", "15", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_16:MonitoringPoint = new MonitoringPoint(60, "LC_GRID_16", "16", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_17:MonitoringPoint = new MonitoringPoint(61, "LC_GRID_17", "17", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_18:MonitoringPoint = new MonitoringPoint(62, "LC_GRID_18", "18", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_19:MonitoringPoint = new MonitoringPoint(63, "LC_GRID_19", "19", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_20:MonitoringPoint = new MonitoringPoint(64, "LC_GRID_20", "20", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_21:MonitoringPoint = new MonitoringPoint(65, "LC_GRID_21", "21", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_22:MonitoringPoint = new MonitoringPoint(66, "LC_GRID_22", "22", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_23:MonitoringPoint = new MonitoringPoint(67, "LC_GRID_23", "23", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_24:MonitoringPoint = new MonitoringPoint(68, "LC_GRID_24", "24", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_25:MonitoringPoint = new MonitoringPoint(69, "LC_GRID_25", "25", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_26:MonitoringPoint = new MonitoringPoint(70, "LC_GRID_26", "26", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_27:MonitoringPoint = new MonitoringPoint(71, "LC_GRID_27", "27", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_28:MonitoringPoint = new MonitoringPoint(72, "LC_GRID_28", "28", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_29:MonitoringPoint = new MonitoringPoint(73, "LC_GRID_29", "29", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_30:MonitoringPoint = new MonitoringPoint(74, "LC_GRID_30", "30", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_31:MonitoringPoint = new MonitoringPoint(75, "LC_GRID_31", "31", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_32:MonitoringPoint = new MonitoringPoint(76, "LC_GRID_32", "32", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_33:MonitoringPoint = new MonitoringPoint(77, "LC_GRID_33", "33", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_34:MonitoringPoint = new MonitoringPoint(78, "LC_GRID_34", "34", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_35:MonitoringPoint = new MonitoringPoint(79, "LC_GRID_35", "35", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_36:MonitoringPoint = new MonitoringPoint(80, "LC_GRID_36", "36", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_37:MonitoringPoint = new MonitoringPoint(81, "LC_GRID_37", "37", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_38:MonitoringPoint = new MonitoringPoint(82, "LC_GRID_38", "38", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_39:MonitoringPoint = new MonitoringPoint(83, "LC_GRID_39", "39", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_40:MonitoringPoint = new MonitoringPoint(84, "LC_GRID_40", "40", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_41:MonitoringPoint = new MonitoringPoint(85, "LC_GRID_41", "41", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_42:MonitoringPoint = new MonitoringPoint(86, "LC_GRID_42", "42", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_43:MonitoringPoint = new MonitoringPoint(87, "LC_GRID_43", "43", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_44:MonitoringPoint = new MonitoringPoint(88, "LC_GRID_44", "44", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_45:MonitoringPoint = new MonitoringPoint(89, "LC_GRID_45", "45", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_46:MonitoringPoint = new MonitoringPoint(90, "LC_GRID_46", "46", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_47:MonitoringPoint = new MonitoringPoint(91, "LC_GRID_47", "47", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_48:MonitoringPoint = new MonitoringPoint(92, "LC_GRID_48", "48", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_49:MonitoringPoint = new MonitoringPoint(93, "LC_GRID_49", "49", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_50:MonitoringPoint = new MonitoringPoint(94, "LC_GRID_50", "50", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_51:MonitoringPoint = new MonitoringPoint(95, "LC_GRID_51", "51", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_52:MonitoringPoint = new MonitoringPoint(96, "LC_GRID_52", "52", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_53:MonitoringPoint = new MonitoringPoint(97, "LC_GRID_53", "53", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_54:MonitoringPoint = new MonitoringPoint(98, "LC_GRID_54", "54", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_55:MonitoringPoint = new MonitoringPoint(99, "LC_GRID_55", "55", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_56:MonitoringPoint = new MonitoringPoint(100, "LC_GRID_56", "56", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_57:MonitoringPoint = new MonitoringPoint(101, "LC_GRID_57", "57", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_58:MonitoringPoint = new MonitoringPoint(102, "LC_GRID_58", "58", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_59:MonitoringPoint = new MonitoringPoint(103, "LC_GRID_59", "59", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_60:MonitoringPoint = new MonitoringPoint(104, "LC_GRID_60", "60", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_61:MonitoringPoint = new MonitoringPoint(105, "LC_GRID_61", "61", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_62:MonitoringPoint = new MonitoringPoint(106, "LC_GRID_62", "62", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_63:MonitoringPoint = new MonitoringPoint(107, "LC_GRID_63", "63", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_64:MonitoringPoint = new MonitoringPoint(108, "LC_GRID_64", "64", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_65:MonitoringPoint = new MonitoringPoint(109, "LC_GRID_65", "65", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_66:MonitoringPoint = new MonitoringPoint(110, "LC_GRID_66", "66", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_67:MonitoringPoint = new MonitoringPoint(111, "LC_GRID_67", "67", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_68:MonitoringPoint = new MonitoringPoint(112, "LC_GRID_68", "68", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_69:MonitoringPoint = new MonitoringPoint(113, "LC_GRID_69", "69", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_70:MonitoringPoint = new MonitoringPoint(114, "LC_GRID_70", "70", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_71:MonitoringPoint = new MonitoringPoint(115, "LC_GRID_71", "71", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_72:MonitoringPoint = new MonitoringPoint(116, "LC_GRID_72", "72", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_73:MonitoringPoint = new MonitoringPoint(117, "LC_GRID_73", "73", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_74:MonitoringPoint = new MonitoringPoint(118, "LC_GRID_74", "74", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_75:MonitoringPoint = new MonitoringPoint(119, "LC_GRID_75", "75", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_76:MonitoringPoint = new MonitoringPoint(120, "LC_GRID_76", "76", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_77:MonitoringPoint = new MonitoringPoint(121, "LC_GRID_77", "77", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_78:MonitoringPoint = new MonitoringPoint(122, "LC_GRID_78", "78", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_79:MonitoringPoint = new MonitoringPoint(123, "LC_GRID_79", "79", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_80:MonitoringPoint = new MonitoringPoint(124, "LC_GRID_80", "80", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_81:MonitoringPoint = new MonitoringPoint(125, "LC_GRID_81", "81", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_82:MonitoringPoint = new MonitoringPoint(126, "LC_GRID_82", "82", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_83:MonitoringPoint = new MonitoringPoint(127, "LC_GRID_83", "83", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_84:MonitoringPoint = new MonitoringPoint(128, "LC_GRID_84", "84", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_85:MonitoringPoint = new MonitoringPoint(129, "LC_GRID_85", "85", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_86:MonitoringPoint = new MonitoringPoint(130, "LC_GRID_86", "86", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_87:MonitoringPoint = new MonitoringPoint(131, "LC_GRID_87", "87", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_88:MonitoringPoint = new MonitoringPoint(132, "LC_GRID_88", "88", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_89:MonitoringPoint = new MonitoringPoint(133, "LC_GRID_89", "89", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_90:MonitoringPoint = new MonitoringPoint(134, "LC_GRID_90", "90", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_91:MonitoringPoint = new MonitoringPoint(135, "LC_GRID_91", "91", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_92:MonitoringPoint = new MonitoringPoint(136, "LC_GRID_92", "92", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_93:MonitoringPoint = new MonitoringPoint(137, "LC_GRID_93", "93", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_94:MonitoringPoint = new MonitoringPoint(138, "LC_GRID_94", "94", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_95:MonitoringPoint = new MonitoringPoint(139, "LC_GRID_95", "95", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_96:MonitoringPoint = new MonitoringPoint(140, "LC_GRID_96", "96", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_97:MonitoringPoint = new MonitoringPoint(141, "LC_GRID_97", "97", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_98:MonitoringPoint = new MonitoringPoint(142, "LC_GRID_98", "98", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_99:MonitoringPoint = new MonitoringPoint(143, "LC_GRID_99", "99", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_100:MonitoringPoint = new MonitoringPoint(144, "LC_GRID_100", "100", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_101:MonitoringPoint = new MonitoringPoint(145, "LC_GRID_101", "101", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_102:MonitoringPoint = new MonitoringPoint(146, "LC_GRID_102", "102", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_103:MonitoringPoint = new MonitoringPoint(147, "LC_GRID_103", "103", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_104:MonitoringPoint = new MonitoringPoint(148, "LC_GRID_104", "104", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_105:MonitoringPoint = new MonitoringPoint(149, "LC_GRID_105", "105", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_106:MonitoringPoint = new MonitoringPoint(150, "LC_GRID_106", "106", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_107:MonitoringPoint = new MonitoringPoint(151, "LC_GRID_107", "107", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_108:MonitoringPoint = new MonitoringPoint(152, "LC_GRID_108", "108", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_109:MonitoringPoint = new MonitoringPoint(153, "LC_GRID_109", "109", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_110:MonitoringPoint = new MonitoringPoint(154, "LC_GRID_110", "110", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_111:MonitoringPoint = new MonitoringPoint(155, "LC_GRID_111", "111", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_112:MonitoringPoint = new MonitoringPoint(156, "LC_GRID_112", "112", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_113:MonitoringPoint = new MonitoringPoint(157, "LC_GRID_113", "113", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_114:MonitoringPoint = new MonitoringPoint(158, "LC_GRID_114", "114", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_115:MonitoringPoint = new MonitoringPoint(159, "LC_GRID_115", "115", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_116:MonitoringPoint = new MonitoringPoint(160, "LC_GRID_116", "116", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_117:MonitoringPoint = new MonitoringPoint(161, "LC_GRID_117", "117", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_118:MonitoringPoint = new MonitoringPoint(162, "LC_GRID_118", "118", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_119:MonitoringPoint = new MonitoringPoint(163, "LC_GRID_119", "119", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_120:MonitoringPoint = new MonitoringPoint(164, "LC_GRID_120", "120", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_121:MonitoringPoint = new MonitoringPoint(165, "LC_GRID_121", "121", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_122:MonitoringPoint = new MonitoringPoint(166, "LC_GRID_122", "122", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_123:MonitoringPoint = new MonitoringPoint(167, "LC_GRID_123", "123", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_124:MonitoringPoint = new MonitoringPoint(168, "LC_GRID_124", "124", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_125:MonitoringPoint = new MonitoringPoint(169, "LC_GRID_125", "125", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_126:MonitoringPoint = new MonitoringPoint(170, "LC_GRID_126", "126", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_127:MonitoringPoint = new MonitoringPoint(171, "LC_GRID_127", "127", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_128:MonitoringPoint = new MonitoringPoint(172, "LC_GRID_128", "128", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_129:MonitoringPoint = new MonitoringPoint(173, "LC_GRID_129", "129", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly LC_GRID_130:MonitoringPoint = new MonitoringPoint(174, "LC_GRID_130", "130", Site.LOPEZ, MonitoringPointType.GRID);
	static readonly SH_GRID_1:MonitoringPoint = new MonitoringPoint(175, "SH_GRID_1", "1", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_2:MonitoringPoint = new MonitoringPoint(176, "SH_GRID_2", "2", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_3:MonitoringPoint = new MonitoringPoint(177, "SH_GRID_3", "3", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_4:MonitoringPoint = new MonitoringPoint(178, "SH_GRID_4", "4", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_5:MonitoringPoint = new MonitoringPoint(179, "SH_GRID_5", "5", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_6:MonitoringPoint = new MonitoringPoint(180, "SH_GRID_6", "6", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_7:MonitoringPoint = new MonitoringPoint(181, "SH_GRID_7", "7", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_8:MonitoringPoint = new MonitoringPoint(182, "SH_GRID_8", "8", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_9:MonitoringPoint = new MonitoringPoint(183, "SH_GRID_9", "9", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_10:MonitoringPoint = new MonitoringPoint(184, "SH_GRID_10", "10", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_11:MonitoringPoint = new MonitoringPoint(185, "SH_GRID_11", "11", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_12:MonitoringPoint = new MonitoringPoint(186, "SH_GRID_12", "12", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_13:MonitoringPoint = new MonitoringPoint(187, "SH_GRID_13", "13", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_14:MonitoringPoint = new MonitoringPoint(188, "SH_GRID_14", "14", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_15:MonitoringPoint = new MonitoringPoint(189, "SH_GRID_15", "15", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_16:MonitoringPoint = new MonitoringPoint(190, "SH_GRID_16", "16", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_17:MonitoringPoint = new MonitoringPoint(191, "SH_GRID_17", "17", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_18:MonitoringPoint = new MonitoringPoint(192, "SH_GRID_18", "18", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_19:MonitoringPoint = new MonitoringPoint(193, "SH_GRID_19", "19", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_20:MonitoringPoint = new MonitoringPoint(194, "SH_GRID_20", "20", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_21:MonitoringPoint = new MonitoringPoint(195, "SH_GRID_21", "21", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_22:MonitoringPoint = new MonitoringPoint(196, "SH_GRID_22", "22", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_23:MonitoringPoint = new MonitoringPoint(197, "SH_GRID_23", "23", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_24:MonitoringPoint = new MonitoringPoint(198, "SH_GRID_24", "24", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_25:MonitoringPoint = new MonitoringPoint(199, "SH_GRID_25", "25", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_26:MonitoringPoint = new MonitoringPoint(200, "SH_GRID_26", "26", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_27:MonitoringPoint = new MonitoringPoint(201, "SH_GRID_27", "27", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_28:MonitoringPoint = new MonitoringPoint(202, "SH_GRID_28", "28", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_29:MonitoringPoint = new MonitoringPoint(203, "SH_GRID_29", "29", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_30:MonitoringPoint = new MonitoringPoint(204, "SH_GRID_30", "30", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_31:MonitoringPoint = new MonitoringPoint(205, "SH_GRID_31", "31", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_32:MonitoringPoint = new MonitoringPoint(206, "SH_GRID_32", "32", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_33:MonitoringPoint = new MonitoringPoint(207, "SH_GRID_33", "33", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_34:MonitoringPoint = new MonitoringPoint(208, "SH_GRID_34", "34", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_35:MonitoringPoint = new MonitoringPoint(209, "SH_GRID_35", "35", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_36:MonitoringPoint = new MonitoringPoint(210, "SH_GRID_36", "36", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_37:MonitoringPoint = new MonitoringPoint(211, "SH_GRID_37", "37", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_38:MonitoringPoint = new MonitoringPoint(212, "SH_GRID_38", "38", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_39:MonitoringPoint = new MonitoringPoint(213, "SH_GRID_39", "39", Site.SHELDON, MonitoringPointType.GRID);
	static readonly SH_GRID_40:MonitoringPoint = new MonitoringPoint(214, "SH_GRID_40", "40", Site.SHELDON, MonitoringPointType.GRID);
	static readonly TC_GRID_1:MonitoringPoint = new MonitoringPoint(215, "TC_GRID_1", "1", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_2:MonitoringPoint = new MonitoringPoint(216, "TC_GRID_2", "2", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_3:MonitoringPoint = new MonitoringPoint(217, "TC_GRID_3", "3", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_4:MonitoringPoint = new MonitoringPoint(218, "TC_GRID_4", "4", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_5:MonitoringPoint = new MonitoringPoint(219, "TC_GRID_5", "5", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_6:MonitoringPoint = new MonitoringPoint(220, "TC_GRID_6", "6", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_7:MonitoringPoint = new MonitoringPoint(221, "TC_GRID_7", "7", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_8:MonitoringPoint = new MonitoringPoint(222, "TC_GRID_8", "8", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_9:MonitoringPoint = new MonitoringPoint(223, "TC_GRID_9", "9", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_10:MonitoringPoint = new MonitoringPoint(224, "TC_GRID_10", "10", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_11:MonitoringPoint = new MonitoringPoint(225, "TC_GRID_11", "11", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_12:MonitoringPoint = new MonitoringPoint(226, "TC_GRID_12", "12", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_13:MonitoringPoint = new MonitoringPoint(227, "TC_GRID_13", "13", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_14:MonitoringPoint = new MonitoringPoint(228, "TC_GRID_14", "14", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_15:MonitoringPoint = new MonitoringPoint(229, "TC_GRID_15", "15", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_16:MonitoringPoint = new MonitoringPoint(230, "TC_GRID_16", "16", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_17:MonitoringPoint = new MonitoringPoint(231, "TC_GRID_17", "17", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_18:MonitoringPoint = new MonitoringPoint(232, "TC_GRID_18", "18", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_19:MonitoringPoint = new MonitoringPoint(233, "TC_GRID_19", "19", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_20:MonitoringPoint = new MonitoringPoint(234, "TC_GRID_20", "20", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_21:MonitoringPoint = new MonitoringPoint(235, "TC_GRID_21", "21", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_22:MonitoringPoint = new MonitoringPoint(236, "TC_GRID_22", "22", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_23:MonitoringPoint = new MonitoringPoint(237, "TC_GRID_23", "23", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_24:MonitoringPoint = new MonitoringPoint(238, "TC_GRID_24", "24", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_25:MonitoringPoint = new MonitoringPoint(239, "TC_GRID_25", "25", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_26:MonitoringPoint = new MonitoringPoint(240, "TC_GRID_26", "26", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_27:MonitoringPoint = new MonitoringPoint(241, "TC_GRID_27", "27", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_28:MonitoringPoint = new MonitoringPoint(242, "TC_GRID_28", "28", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_29:MonitoringPoint = new MonitoringPoint(243, "TC_GRID_29", "29", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_30:MonitoringPoint = new MonitoringPoint(244, "TC_GRID_30", "30", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_31:MonitoringPoint = new MonitoringPoint(245, "TC_GRID_31", "31", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_32:MonitoringPoint = new MonitoringPoint(246, "TC_GRID_32", "32", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_33:MonitoringPoint = new MonitoringPoint(247, "TC_GRID_33", "33", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_34:MonitoringPoint = new MonitoringPoint(248, "TC_GRID_34", "34", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_35:MonitoringPoint = new MonitoringPoint(249, "TC_GRID_35", "35", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_36:MonitoringPoint = new MonitoringPoint(250, "TC_GRID_36", "36", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_37:MonitoringPoint = new MonitoringPoint(251, "TC_GRID_37", "37", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_38:MonitoringPoint = new MonitoringPoint(252, "TC_GRID_38", "38", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_39:MonitoringPoint = new MonitoringPoint(253, "TC_GRID_39", "39", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_40:MonitoringPoint = new MonitoringPoint(254, "TC_GRID_40", "40", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_41:MonitoringPoint = new MonitoringPoint(255, "TC_GRID_41", "41", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_42:MonitoringPoint = new MonitoringPoint(256, "TC_GRID_42", "42", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_43:MonitoringPoint = new MonitoringPoint(257, "TC_GRID_43", "43", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_44:MonitoringPoint = new MonitoringPoint(258, "TC_GRID_44", "44", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_45:MonitoringPoint = new MonitoringPoint(259, "TC_GRID_45", "45", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_46:MonitoringPoint = new MonitoringPoint(260, "TC_GRID_46", "46", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_47:MonitoringPoint = new MonitoringPoint(261, "TC_GRID_47", "47", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_48:MonitoringPoint = new MonitoringPoint(262, "TC_GRID_48", "48", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_49:MonitoringPoint = new MonitoringPoint(263, "TC_GRID_49", "49", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_50:MonitoringPoint = new MonitoringPoint(264, "TC_GRID_50", "50", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_51:MonitoringPoint = new MonitoringPoint(265, "TC_GRID_51", "51", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_52:MonitoringPoint = new MonitoringPoint(266, "TC_GRID_52", "52", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_53:MonitoringPoint = new MonitoringPoint(267, "TC_GRID_53", "53", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_54:MonitoringPoint = new MonitoringPoint(268, "TC_GRID_54", "54", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_55:MonitoringPoint = new MonitoringPoint(269, "TC_GRID_55", "55", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_56:MonitoringPoint = new MonitoringPoint(270, "TC_GRID_56", "56", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_57:MonitoringPoint = new MonitoringPoint(271, "TC_GRID_57", "57", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_58:MonitoringPoint = new MonitoringPoint(272, "TC_GRID_58", "58", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_59:MonitoringPoint = new MonitoringPoint(273, "TC_GRID_59", "59", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_60:MonitoringPoint = new MonitoringPoint(274, "TC_GRID_60", "60", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_61:MonitoringPoint = new MonitoringPoint(275, "TC_GRID_61", "61", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_62:MonitoringPoint = new MonitoringPoint(276, "TC_GRID_62", "62", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_63:MonitoringPoint = new MonitoringPoint(277, "TC_GRID_63", "63", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_64:MonitoringPoint = new MonitoringPoint(278, "TC_GRID_64", "64", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_65:MonitoringPoint = new MonitoringPoint(279, "TC_GRID_65", "65", Site.TOYON, MonitoringPointType.GRID);
	static readonly TC_GRID_66:MonitoringPoint = new MonitoringPoint(280, "TC_GRID_66", "66", Site.TOYON, MonitoringPointType.GRID);
	static readonly BC_GROUNDWATER_BC_01:MonitoringPoint = new MonitoringPoint(281, "BC_GROUNDWATER_BC_01", "BC-01", Site.BISHOPS, MonitoringPointType.GROUNDWATER);
	static readonly BC_GROUNDWATER_BC_02:MonitoringPoint = new MonitoringPoint(282, "BC_GROUNDWATER_BC_02", "BC-02", Site.BISHOPS, MonitoringPointType.GROUNDWATER);
	static readonly BC_GROUNDWATER_BC_02A:MonitoringPoint = new MonitoringPoint(283, "BC_GROUNDWATER_BC_02A", "BC-02A", Site.BISHOPS, MonitoringPointType.GROUNDWATER);
	static readonly BC_GROUNDWATER_BC_03:MonitoringPoint = new MonitoringPoint(284, "BC_GROUNDWATER_BC_03", "BC-03", Site.BISHOPS, MonitoringPointType.GROUNDWATER);
	static readonly BC_GROUNDWATER_BC_04:MonitoringPoint = new MonitoringPoint(285, "BC_GROUNDWATER_BC_04", "BC-04", Site.BISHOPS, MonitoringPointType.GROUNDWATER);
	static readonly BC_GROUNDWATER_BC_L:MonitoringPoint = new MonitoringPoint(286, "BC_GROUNDWATER_BC_L", "BC-L", Site.BISHOPS, MonitoringPointType.GROUNDWATER);
	static readonly BC_GROUNDWATER_BC_LW:MonitoringPoint = new MonitoringPoint(287, "BC_GROUNDWATER_BC_LW", "BC-LW", Site.BISHOPS, MonitoringPointType.GROUNDWATER);
	static readonly BR_GROUNDWATER_MW_1:MonitoringPoint = new MonitoringPoint(288, "BR_GROUNDWATER_MW_1", "MW-1", Site.BRANFORD, MonitoringPointType.GROUNDWATER);
	static readonly BR_GROUNDWATER_MW_2:MonitoringPoint = new MonitoringPoint(289, "BR_GROUNDWATER_MW_2", "MW-2", Site.BRANFORD, MonitoringPointType.GROUNDWATER);
	static readonly BR_GROUNDWATER_MW_3:MonitoringPoint = new MonitoringPoint(290, "BR_GROUNDWATER_MW_3", "MW-3", Site.BRANFORD, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_LW_1:MonitoringPoint = new MonitoringPoint(291, "GA_GROUNDWATER_LW_1", "LW-1", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_LW_2:MonitoringPoint = new MonitoringPoint(292, "GA_GROUNDWATER_LW_2", "LW-2", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_MW_1:MonitoringPoint = new MonitoringPoint(293, "GA_GROUNDWATER_MW_1", "MW-1", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_MW_2:MonitoringPoint = new MonitoringPoint(294, "GA_GROUNDWATER_MW_2", "MW-2", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_MW_3:MonitoringPoint = new MonitoringPoint(295, "GA_GROUNDWATER_MW_3", "MW-3", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_MW_A:MonitoringPoint = new MonitoringPoint(296, "GA_GROUNDWATER_MW_A", "MW-A", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_MW_B:MonitoringPoint = new MonitoringPoint(297, "GA_GROUNDWATER_MW_B", "MW-B", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_MW_C:MonitoringPoint = new MonitoringPoint(298, "GA_GROUNDWATER_MW_C", "MW-C", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly GA_GROUNDWATER_MW_D:MonitoringPoint = new MonitoringPoint(299, "GA_GROUNDWATER_MW_D", "MW-D", Site.GAFFEY, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW92_1:MonitoringPoint = new MonitoringPoint(300, "LC_GROUNDWATER_MW92_1", "MW92-1", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW92_2:MonitoringPoint = new MonitoringPoint(301, "LC_GROUNDWATER_MW92_2", "MW92-2", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW92_3:MonitoringPoint = new MonitoringPoint(302, "LC_GROUNDWATER_MW92_3", "MW92-3", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW93_1:MonitoringPoint = new MonitoringPoint(303, "LC_GROUNDWATER_MW93_1", "MW93-1", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW93_2:MonitoringPoint = new MonitoringPoint(304, "LC_GROUNDWATER_MW93_2", "MW93-2", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW95_1:MonitoringPoint = new MonitoringPoint(305, "LC_GROUNDWATER_MW95_1", "MW95-1", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW95_2:MonitoringPoint = new MonitoringPoint(306, "LC_GROUNDWATER_MW95_2", "MW95-2", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW95_3:MonitoringPoint = new MonitoringPoint(307, "LC_GROUNDWATER_MW95_3", "MW95-3", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW95_4:MonitoringPoint = new MonitoringPoint(308, "LC_GROUNDWATER_MW95_4", "MW95-4", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW95_5:MonitoringPoint = new MonitoringPoint(309, "LC_GROUNDWATER_MW95_5", "MW95-5", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly LC_GROUNDWATER_MW95_6:MonitoringPoint = new MonitoringPoint(310, "LC_GROUNDWATER_MW95_6", "MW95-6", Site.LOPEZ, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_4897A:MonitoringPoint = new MonitoringPoint(311, "SH_GROUNDWATER_4897A", "4897A", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_4897B:MonitoringPoint = new MonitoringPoint(312, "SH_GROUNDWATER_4897B", "4897B", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_4897C:MonitoringPoint = new MonitoringPoint(313, "SH_GROUNDWATER_4897C", "4897C", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_4897D:MonitoringPoint = new MonitoringPoint(314, "SH_GROUNDWATER_4897D", "4897D", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_1:MonitoringPoint = new MonitoringPoint(315, "SH_GROUNDWATER_IT_SA_1", "IT-SA-1", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_2:MonitoringPoint = new MonitoringPoint(316, "SH_GROUNDWATER_IT_SA_2", "IT-SA-2", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_3:MonitoringPoint = new MonitoringPoint(317, "SH_GROUNDWATER_IT_SA_3", "IT-SA-3", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_4:MonitoringPoint = new MonitoringPoint(318, "SH_GROUNDWATER_IT_SA_4", "IT-SA-4", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_5:MonitoringPoint = new MonitoringPoint(319, "SH_GROUNDWATER_IT_SA_5", "IT-SA-5", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_6:MonitoringPoint = new MonitoringPoint(320, "SH_GROUNDWATER_IT_SA_6", "IT-SA-6", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_7:MonitoringPoint = new MonitoringPoint(321, "SH_GROUNDWATER_IT_SA_7", "IT-SA-7", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_IT_SA_8:MonitoringPoint = new MonitoringPoint(322, "SH_GROUNDWATER_IT_SA_8", "IT-SA-8", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_SA_1A:MonitoringPoint = new MonitoringPoint(323, "SH_GROUNDWATER_SA_1A", "SA-1A", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_SA_3A:MonitoringPoint = new MonitoringPoint(324, "SH_GROUNDWATER_SA_3A", "SA-3A", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_SA_8A:MonitoringPoint = new MonitoringPoint(325, "SH_GROUNDWATER_SA_8A", "SA-8A", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly SH_GROUNDWATER_SA_LW:MonitoringPoint = new MonitoringPoint(326, "SH_GROUNDWATER_SA_LW", "SA-LW", Site.SHELDON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_1:MonitoringPoint = new MonitoringPoint(327, "TC_GROUNDWATER_MW_1", "MW-1", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_2:MonitoringPoint = new MonitoringPoint(328, "TC_GROUNDWATER_MW_2", "MW-2", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_3:MonitoringPoint = new MonitoringPoint(329, "TC_GROUNDWATER_MW_3", "MW-3", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_4:MonitoringPoint = new MonitoringPoint(330, "TC_GROUNDWATER_MW_4", "MW-4", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_4A:MonitoringPoint = new MonitoringPoint(331, "TC_GROUNDWATER_MW_4A", "MW-4A", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_5:MonitoringPoint = new MonitoringPoint(332, "TC_GROUNDWATER_MW_5", "MW-5", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_6:MonitoringPoint = new MonitoringPoint(333, "TC_GROUNDWATER_MW_6", "MW-6", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_7:MonitoringPoint = new MonitoringPoint(334, "TC_GROUNDWATER_MW_7", "MW-7", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_8:MonitoringPoint = new MonitoringPoint(335, "TC_GROUNDWATER_MW_8", "MW-8", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_8A:MonitoringPoint = new MonitoringPoint(336, "TC_GROUNDWATER_MW_8A", "MW-8A", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_MW_9:MonitoringPoint = new MonitoringPoint(337, "TC_GROUNDWATER_MW_9", "MW-9", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_TII_1:MonitoringPoint = new MonitoringPoint(338, "TC_GROUNDWATER_TII_1", "TII-1", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_TII_2:MonitoringPoint = new MonitoringPoint(339, "TC_GROUNDWATER_TII_2", "TII-2", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly TC_GROUNDWATER_TII_3:MonitoringPoint = new MonitoringPoint(340, "TC_GROUNDWATER_TII_3", "TII-3", Site.TOYON, MonitoringPointType.GROUNDWATER);
	static readonly GA_LFG_IN:MonitoringPoint = new MonitoringPoint(341, "GA_LFG_IN", "IN", Site.GAFFEY, MonitoringPointType.LFG);
	static readonly GA_LFG_MID:MonitoringPoint = new MonitoringPoint(342, "GA_LFG_MID", "MID", Site.GAFFEY, MonitoringPointType.LFG);
	static readonly GA_LFG_OUT:MonitoringPoint = new MonitoringPoint(343, "GA_LFG_OUT", "OUT", Site.GAFFEY, MonitoringPointType.LFG);
	static readonly LC_LFG_CO:MonitoringPoint = new MonitoringPoint(344, "LC_LFG_CO", "CO", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F1:MonitoringPoint = new MonitoringPoint(345, "LC_LFG_F1", "F1", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F2:MonitoringPoint = new MonitoringPoint(346, "LC_LFG_F2", "F2", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F3:MonitoringPoint = new MonitoringPoint(347, "LC_LFG_F3", "F3", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F4:MonitoringPoint = new MonitoringPoint(348, "LC_LFG_F4", "F4", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F5:MonitoringPoint = new MonitoringPoint(349, "LC_LFG_F5", "F5", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F6:MonitoringPoint = new MonitoringPoint(350, "LC_LFG_F6", "F6", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F7:MonitoringPoint = new MonitoringPoint(351, "LC_LFG_F7", "F7", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F8:MonitoringPoint = new MonitoringPoint(352, "LC_LFG_F8", "F8", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly LC_LFG_F9:MonitoringPoint = new MonitoringPoint(353, "LC_LFG_F9", "F9", Site.LOPEZ, MonitoringPointType.LFG);
	static readonly SH_LFG_F1:MonitoringPoint = new MonitoringPoint(354, "SH_LFG_F1", "F1", Site.SHELDON, MonitoringPointType.LFG);
	static readonly SH_LFG_F2:MonitoringPoint = new MonitoringPoint(355, "SH_LFG_F2", "F2", Site.SHELDON, MonitoringPointType.LFG);
	static readonly TC_LFG_CO:MonitoringPoint = new MonitoringPoint(356, "TC_LFG_CO", "CO", Site.TOYON, MonitoringPointType.LFG);
	static readonly TC_LFG_FL:MonitoringPoint = new MonitoringPoint(357, "TC_LFG_FL", "FL", Site.TOYON, MonitoringPointType.LFG);
	static readonly LC_LEACHATE_AB:MonitoringPoint = new MonitoringPoint(358, "LC_LEACHATE_AB", "AB+", Site.LOPEZ, MonitoringPointType.LEACHATE);
	static readonly LC_LEACHATE_C:MonitoringPoint = new MonitoringPoint(359, "LC_LEACHATE_C", "C", Site.LOPEZ, MonitoringPointType.LEACHATE);
	static readonly TC_LEACHATE_LB_1N:MonitoringPoint = new MonitoringPoint(360, "TC_LEACHATE_LB_1N", "LB-1N", Site.TOYON, MonitoringPointType.LEACHATE);
	static readonly TC_LEACHATE_LB_1S:MonitoringPoint = new MonitoringPoint(361, "TC_LEACHATE_LB_1S", "LB-1S", Site.TOYON, MonitoringPointType.LEACHATE);
	static readonly GP_PILE_A1:MonitoringPoint = new MonitoringPoint(362, "GP_PILE_A1", "A1", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A2:MonitoringPoint = new MonitoringPoint(363, "GP_PILE_A2", "A2", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A3:MonitoringPoint = new MonitoringPoint(364, "GP_PILE_A3", "A3", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A4:MonitoringPoint = new MonitoringPoint(365, "GP_PILE_A4", "A4", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A5:MonitoringPoint = new MonitoringPoint(366, "GP_PILE_A5", "A5", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A6:MonitoringPoint = new MonitoringPoint(367, "GP_PILE_A6", "A6", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A7:MonitoringPoint = new MonitoringPoint(368, "GP_PILE_A7", "A7", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A8:MonitoringPoint = new MonitoringPoint(369, "GP_PILE_A8", "A8", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_A9:MonitoringPoint = new MonitoringPoint(370, "GP_PILE_A9", "A9", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B1:MonitoringPoint = new MonitoringPoint(371, "GP_PILE_B1", "B1", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B2:MonitoringPoint = new MonitoringPoint(372, "GP_PILE_B2", "B2", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B3:MonitoringPoint = new MonitoringPoint(373, "GP_PILE_B3", "B3", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B4:MonitoringPoint = new MonitoringPoint(374, "GP_PILE_B4", "B4", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B5:MonitoringPoint = new MonitoringPoint(375, "GP_PILE_B5", "B5", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B6:MonitoringPoint = new MonitoringPoint(376, "GP_PILE_B6", "B6", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B7:MonitoringPoint = new MonitoringPoint(377, "GP_PILE_B7", "B7", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B8:MonitoringPoint = new MonitoringPoint(378, "GP_PILE_B8", "B8", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly GP_PILE_B9:MonitoringPoint = new MonitoringPoint(379, "GP_PILE_B9", "B9", Site.GRIFFITH_PARK, MonitoringPointType.PILE);
	static readonly LC_PILE_1:MonitoringPoint = new MonitoringPoint(380, "LC_PILE_1", "1", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_2:MonitoringPoint = new MonitoringPoint(381, "LC_PILE_2", "2", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_3:MonitoringPoint = new MonitoringPoint(382, "LC_PILE_3", "3", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_4:MonitoringPoint = new MonitoringPoint(383, "LC_PILE_4", "4", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_5:MonitoringPoint = new MonitoringPoint(384, "LC_PILE_5", "5", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_6:MonitoringPoint = new MonitoringPoint(385, "LC_PILE_6", "6", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_7:MonitoringPoint = new MonitoringPoint(386, "LC_PILE_7", "7", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_8:MonitoringPoint = new MonitoringPoint(387, "LC_PILE_8", "8", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_9:MonitoringPoint = new MonitoringPoint(388, "LC_PILE_9", "9", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_10:MonitoringPoint = new MonitoringPoint(389, "LC_PILE_10", "10", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_11:MonitoringPoint = new MonitoringPoint(390, "LC_PILE_11", "11", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_12:MonitoringPoint = new MonitoringPoint(391, "LC_PILE_12", "12", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_13:MonitoringPoint = new MonitoringPoint(392, "LC_PILE_13", "13", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_14:MonitoringPoint = new MonitoringPoint(393, "LC_PILE_14", "14", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_15:MonitoringPoint = new MonitoringPoint(394, "LC_PILE_15", "15", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_16:MonitoringPoint = new MonitoringPoint(395, "LC_PILE_16", "16", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_17:MonitoringPoint = new MonitoringPoint(396, "LC_PILE_17", "17", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_18:MonitoringPoint = new MonitoringPoint(397, "LC_PILE_18", "18", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_19:MonitoringPoint = new MonitoringPoint(398, "LC_PILE_19", "19", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_20:MonitoringPoint = new MonitoringPoint(399, "LC_PILE_20", "20", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_21:MonitoringPoint = new MonitoringPoint(400, "LC_PILE_21", "21", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_22:MonitoringPoint = new MonitoringPoint(401, "LC_PILE_22", "22", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_23:MonitoringPoint = new MonitoringPoint(402, "LC_PILE_23", "23", Site.LCEC, MonitoringPointType.PILE);
	static readonly LC_PILE_24:MonitoringPoint = new MonitoringPoint(403, "LC_PILE_24", "24", Site.LCEC, MonitoringPointType.PILE);
	static readonly BC_PROBE_01A:MonitoringPoint = new MonitoringPoint(404, "BC_PROBE_01A", "01A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_02A:MonitoringPoint = new MonitoringPoint(405, "BC_PROBE_02A", "02A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_02B:MonitoringPoint = new MonitoringPoint(406, "BC_PROBE_02B", "02B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_02C:MonitoringPoint = new MonitoringPoint(407, "BC_PROBE_02C", "02C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_03A:MonitoringPoint = new MonitoringPoint(408, "BC_PROBE_03A", "03A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_03B:MonitoringPoint = new MonitoringPoint(409, "BC_PROBE_03B", "03B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_03C:MonitoringPoint = new MonitoringPoint(410, "BC_PROBE_03C", "03C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_03D:MonitoringPoint = new MonitoringPoint(411, "BC_PROBE_03D", "03D", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_04A:MonitoringPoint = new MonitoringPoint(412, "BC_PROBE_04A", "04A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_04B:MonitoringPoint = new MonitoringPoint(413, "BC_PROBE_04B", "04B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_04C:MonitoringPoint = new MonitoringPoint(414, "BC_PROBE_04C", "04C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_04D:MonitoringPoint = new MonitoringPoint(415, "BC_PROBE_04D", "04D", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_05A:MonitoringPoint = new MonitoringPoint(416, "BC_PROBE_05A", "05A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_05B:MonitoringPoint = new MonitoringPoint(417, "BC_PROBE_05B", "05B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_05C:MonitoringPoint = new MonitoringPoint(418, "BC_PROBE_05C", "05C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_05D:MonitoringPoint = new MonitoringPoint(419, "BC_PROBE_05D", "05D", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_06A:MonitoringPoint = new MonitoringPoint(420, "BC_PROBE_06A", "06A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_06B:MonitoringPoint = new MonitoringPoint(421, "BC_PROBE_06B", "06B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_06C:MonitoringPoint = new MonitoringPoint(422, "BC_PROBE_06C", "06C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_07A:MonitoringPoint = new MonitoringPoint(423, "BC_PROBE_07A", "07A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_07B:MonitoringPoint = new MonitoringPoint(424, "BC_PROBE_07B", "07B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_07C:MonitoringPoint = new MonitoringPoint(425, "BC_PROBE_07C", "07C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_08A:MonitoringPoint = new MonitoringPoint(426, "BC_PROBE_08A", "08A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_08B:MonitoringPoint = new MonitoringPoint(427, "BC_PROBE_08B", "08B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_08C:MonitoringPoint = new MonitoringPoint(428, "BC_PROBE_08C", "08C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_09A:MonitoringPoint = new MonitoringPoint(429, "BC_PROBE_09A", "09A", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_09B:MonitoringPoint = new MonitoringPoint(430, "BC_PROBE_09B", "09B", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly BC_PROBE_09C:MonitoringPoint = new MonitoringPoint(431, "BC_PROBE_09C", "09C", Site.BISHOPS, MonitoringPointType.PROBE);
	static readonly GA_PROBE_01A:MonitoringPoint = new MonitoringPoint(432, "GA_PROBE_01A", "01A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_01B:MonitoringPoint = new MonitoringPoint(433, "GA_PROBE_01B", "01B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_01C:MonitoringPoint = new MonitoringPoint(434, "GA_PROBE_01C", "01C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_02A:MonitoringPoint = new MonitoringPoint(435, "GA_PROBE_02A", "02A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_03A:MonitoringPoint = new MonitoringPoint(436, "GA_PROBE_03A", "03A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_04A:MonitoringPoint = new MonitoringPoint(437, "GA_PROBE_04A", "04A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_05A:MonitoringPoint = new MonitoringPoint(438, "GA_PROBE_05A", "05A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_06A:MonitoringPoint = new MonitoringPoint(439, "GA_PROBE_06A", "06A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_06B:MonitoringPoint = new MonitoringPoint(440, "GA_PROBE_06B", "06B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_06C:MonitoringPoint = new MonitoringPoint(441, "GA_PROBE_06C", "06C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_07A:MonitoringPoint = new MonitoringPoint(442, "GA_PROBE_07A", "07A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_07B:MonitoringPoint = new MonitoringPoint(443, "GA_PROBE_07B", "07B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_07C:MonitoringPoint = new MonitoringPoint(444, "GA_PROBE_07C", "07C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_08A:MonitoringPoint = new MonitoringPoint(445, "GA_PROBE_08A", "08A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_08B:MonitoringPoint = new MonitoringPoint(446, "GA_PROBE_08B", "08B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_08C:MonitoringPoint = new MonitoringPoint(447, "GA_PROBE_08C", "08C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_09A:MonitoringPoint = new MonitoringPoint(448, "GA_PROBE_09A", "09A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_09B:MonitoringPoint = new MonitoringPoint(449, "GA_PROBE_09B", "09B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_09C:MonitoringPoint = new MonitoringPoint(450, "GA_PROBE_09C", "09C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_10A:MonitoringPoint = new MonitoringPoint(451, "GA_PROBE_10A", "10A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_10B:MonitoringPoint = new MonitoringPoint(452, "GA_PROBE_10B", "10B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_10C:MonitoringPoint = new MonitoringPoint(453, "GA_PROBE_10C", "10C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_11A:MonitoringPoint = new MonitoringPoint(454, "GA_PROBE_11A", "11A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_11B:MonitoringPoint = new MonitoringPoint(455, "GA_PROBE_11B", "11B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_11C:MonitoringPoint = new MonitoringPoint(456, "GA_PROBE_11C", "11C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_12A:MonitoringPoint = new MonitoringPoint(457, "GA_PROBE_12A", "12A", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_12B:MonitoringPoint = new MonitoringPoint(458, "GA_PROBE_12B", "12B", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly GA_PROBE_12C:MonitoringPoint = new MonitoringPoint(459, "GA_PROBE_12C", "12C", Site.GAFFEY, MonitoringPointType.PROBE);
	static readonly LC_PROBE_01A:MonitoringPoint = new MonitoringPoint(460, "LC_PROBE_01A", "01A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_02A:MonitoringPoint = new MonitoringPoint(461, "LC_PROBE_02A", "02A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_03A:MonitoringPoint = new MonitoringPoint(462, "LC_PROBE_03A", "03A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_04A:MonitoringPoint = new MonitoringPoint(463, "LC_PROBE_04A", "04A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_05A:MonitoringPoint = new MonitoringPoint(464, "LC_PROBE_05A", "05A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_06A:MonitoringPoint = new MonitoringPoint(465, "LC_PROBE_06A", "06A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_07A:MonitoringPoint = new MonitoringPoint(466, "LC_PROBE_07A", "07A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_08A:MonitoringPoint = new MonitoringPoint(467, "LC_PROBE_08A", "08A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_09A:MonitoringPoint = new MonitoringPoint(468, "LC_PROBE_09A", "09A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_10A:MonitoringPoint = new MonitoringPoint(469, "LC_PROBE_10A", "10A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_10B:MonitoringPoint = new MonitoringPoint(470, "LC_PROBE_10B", "10B", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_10C:MonitoringPoint = new MonitoringPoint(471, "LC_PROBE_10C", "10C", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_10D:MonitoringPoint = new MonitoringPoint(472, "LC_PROBE_10D", "10D", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_13A:MonitoringPoint = new MonitoringPoint(473, "LC_PROBE_13A", "13A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_14A:MonitoringPoint = new MonitoringPoint(474, "LC_PROBE_14A", "14A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_15A:MonitoringPoint = new MonitoringPoint(475, "LC_PROBE_15A", "15A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_16A:MonitoringPoint = new MonitoringPoint(476, "LC_PROBE_16A", "16A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_17A:MonitoringPoint = new MonitoringPoint(477, "LC_PROBE_17A", "17A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_18A:MonitoringPoint = new MonitoringPoint(478, "LC_PROBE_18A", "18A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_19A:MonitoringPoint = new MonitoringPoint(479, "LC_PROBE_19A", "19A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_20A:MonitoringPoint = new MonitoringPoint(480, "LC_PROBE_20A", "20A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_21A:MonitoringPoint = new MonitoringPoint(481, "LC_PROBE_21A", "21A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_23A:MonitoringPoint = new MonitoringPoint(482, "LC_PROBE_23A", "23A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_24A:MonitoringPoint = new MonitoringPoint(483, "LC_PROBE_24A", "24A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_24B:MonitoringPoint = new MonitoringPoint(484, "LC_PROBE_24B", "24B", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_24C:MonitoringPoint = new MonitoringPoint(485, "LC_PROBE_24C", "24C", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_24D:MonitoringPoint = new MonitoringPoint(486, "LC_PROBE_24D", "24D", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_27A:MonitoringPoint = new MonitoringPoint(487, "LC_PROBE_27A", "27A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_28A:MonitoringPoint = new MonitoringPoint(488, "LC_PROBE_28A", "28A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_29A:MonitoringPoint = new MonitoringPoint(489, "LC_PROBE_29A", "29A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_30A:MonitoringPoint = new MonitoringPoint(490, "LC_PROBE_30A", "30A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_31A:MonitoringPoint = new MonitoringPoint(491, "LC_PROBE_31A", "31A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly LC_PROBE_32A:MonitoringPoint = new MonitoringPoint(492, "LC_PROBE_32A", "32A", Site.LOPEZ, MonitoringPointType.PROBE);
	static readonly PH_PROBE_01A:MonitoringPoint = new MonitoringPoint(493, "PH_PROBE_01A", "01A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_01B:MonitoringPoint = new MonitoringPoint(494, "PH_PROBE_01B", "01B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_01C:MonitoringPoint = new MonitoringPoint(495, "PH_PROBE_01C", "01C", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_02A:MonitoringPoint = new MonitoringPoint(496, "PH_PROBE_02A", "02A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_02B:MonitoringPoint = new MonitoringPoint(497, "PH_PROBE_02B", "02B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_02C:MonitoringPoint = new MonitoringPoint(498, "PH_PROBE_02C", "02C", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_03A:MonitoringPoint = new MonitoringPoint(499, "PH_PROBE_03A", "03A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_03B:MonitoringPoint = new MonitoringPoint(500, "PH_PROBE_03B", "03B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_03C:MonitoringPoint = new MonitoringPoint(501, "PH_PROBE_03C", "03C", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_04A:MonitoringPoint = new MonitoringPoint(502, "PH_PROBE_04A", "04A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_04B:MonitoringPoint = new MonitoringPoint(503, "PH_PROBE_04B", "04B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_04C:MonitoringPoint = new MonitoringPoint(504, "PH_PROBE_04C", "04C", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_05A:MonitoringPoint = new MonitoringPoint(505, "PH_PROBE_05A", "05A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_05B:MonitoringPoint = new MonitoringPoint(506, "PH_PROBE_05B", "05B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_05C:MonitoringPoint = new MonitoringPoint(507, "PH_PROBE_05C", "05C", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_06A:MonitoringPoint = new MonitoringPoint(508, "PH_PROBE_06A", "06A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_06B:MonitoringPoint = new MonitoringPoint(509, "PH_PROBE_06B", "06B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_07A:MonitoringPoint = new MonitoringPoint(510, "PH_PROBE_07A", "07A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_07B:MonitoringPoint = new MonitoringPoint(511, "PH_PROBE_07B", "07B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_08A:MonitoringPoint = new MonitoringPoint(512, "PH_PROBE_08A", "08A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_08B:MonitoringPoint = new MonitoringPoint(513, "PH_PROBE_08B", "08B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_09A:MonitoringPoint = new MonitoringPoint(514, "PH_PROBE_09A", "09A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_09B:MonitoringPoint = new MonitoringPoint(515, "PH_PROBE_09B", "09B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_10A:MonitoringPoint = new MonitoringPoint(516, "PH_PROBE_10A", "10A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_10B:MonitoringPoint = new MonitoringPoint(517, "PH_PROBE_10B", "10B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_11A:MonitoringPoint = new MonitoringPoint(518, "PH_PROBE_11A", "11A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_11B:MonitoringPoint = new MonitoringPoint(519, "PH_PROBE_11B", "11B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_12A:MonitoringPoint = new MonitoringPoint(520, "PH_PROBE_12A", "12A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_12B:MonitoringPoint = new MonitoringPoint(521, "PH_PROBE_12B", "12B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_13A:MonitoringPoint = new MonitoringPoint(522, "PH_PROBE_13A", "13A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_13B:MonitoringPoint = new MonitoringPoint(523, "PH_PROBE_13B", "13B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_14A:MonitoringPoint = new MonitoringPoint(524, "PH_PROBE_14A", "14A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_14B:MonitoringPoint = new MonitoringPoint(525, "PH_PROBE_14B", "14B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_15A:MonitoringPoint = new MonitoringPoint(526, "PH_PROBE_15A", "15A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_15B:MonitoringPoint = new MonitoringPoint(527, "PH_PROBE_15B", "15B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_16A:MonitoringPoint = new MonitoringPoint(528, "PH_PROBE_16A", "16A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_16B:MonitoringPoint = new MonitoringPoint(529, "PH_PROBE_16B", "16B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_17A:MonitoringPoint = new MonitoringPoint(530, "PH_PROBE_17A", "17A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_17B:MonitoringPoint = new MonitoringPoint(531, "PH_PROBE_17B", "17B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_18A:MonitoringPoint = new MonitoringPoint(532, "PH_PROBE_18A", "18A", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly PH_PROBE_18B:MonitoringPoint = new MonitoringPoint(533, "PH_PROBE_18B", "18B", Site.POLY_HIGH, MonitoringPointType.PROBE);
	static readonly SH_PROBE_01A:MonitoringPoint = new MonitoringPoint(534, "SH_PROBE_01A", "01A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_01B:MonitoringPoint = new MonitoringPoint(535, "SH_PROBE_01B", "01B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_01C:MonitoringPoint = new MonitoringPoint(536, "SH_PROBE_01C", "01C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_01D:MonitoringPoint = new MonitoringPoint(537, "SH_PROBE_01D", "01D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_01E:MonitoringPoint = new MonitoringPoint(538, "SH_PROBE_01E", "01E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_02A:MonitoringPoint = new MonitoringPoint(539, "SH_PROBE_02A", "02A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_02B:MonitoringPoint = new MonitoringPoint(540, "SH_PROBE_02B", "02B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_02C:MonitoringPoint = new MonitoringPoint(541, "SH_PROBE_02C", "02C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_02D:MonitoringPoint = new MonitoringPoint(542, "SH_PROBE_02D", "02D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_02E:MonitoringPoint = new MonitoringPoint(543, "SH_PROBE_02E", "02E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_03A:MonitoringPoint = new MonitoringPoint(544, "SH_PROBE_03A", "03A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_03B:MonitoringPoint = new MonitoringPoint(545, "SH_PROBE_03B", "03B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_03C:MonitoringPoint = new MonitoringPoint(546, "SH_PROBE_03C", "03C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_03D:MonitoringPoint = new MonitoringPoint(547, "SH_PROBE_03D", "03D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_03E:MonitoringPoint = new MonitoringPoint(548, "SH_PROBE_03E", "03E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_04A:MonitoringPoint = new MonitoringPoint(549, "SH_PROBE_04A", "04A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_04B:MonitoringPoint = new MonitoringPoint(550, "SH_PROBE_04B", "04B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_04C:MonitoringPoint = new MonitoringPoint(551, "SH_PROBE_04C", "04C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_04D:MonitoringPoint = new MonitoringPoint(552, "SH_PROBE_04D", "04D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_04E:MonitoringPoint = new MonitoringPoint(553, "SH_PROBE_04E", "04E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_05A:MonitoringPoint = new MonitoringPoint(554, "SH_PROBE_05A", "05A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_05B:MonitoringPoint = new MonitoringPoint(555, "SH_PROBE_05B", "05B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_05C:MonitoringPoint = new MonitoringPoint(556, "SH_PROBE_05C", "05C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_05D:MonitoringPoint = new MonitoringPoint(557, "SH_PROBE_05D", "05D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_05E:MonitoringPoint = new MonitoringPoint(558, "SH_PROBE_05E", "05E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_06A:MonitoringPoint = new MonitoringPoint(559, "SH_PROBE_06A", "06A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_06B:MonitoringPoint = new MonitoringPoint(560, "SH_PROBE_06B", "06B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_06C:MonitoringPoint = new MonitoringPoint(561, "SH_PROBE_06C", "06C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_06D:MonitoringPoint = new MonitoringPoint(562, "SH_PROBE_06D", "06D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_06E:MonitoringPoint = new MonitoringPoint(563, "SH_PROBE_06E", "06E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_07A:MonitoringPoint = new MonitoringPoint(564, "SH_PROBE_07A", "07A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_07B:MonitoringPoint = new MonitoringPoint(565, "SH_PROBE_07B", "07B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_07C:MonitoringPoint = new MonitoringPoint(566, "SH_PROBE_07C", "07C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_07D:MonitoringPoint = new MonitoringPoint(567, "SH_PROBE_07D", "07D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_07E:MonitoringPoint = new MonitoringPoint(568, "SH_PROBE_07E", "07E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_08A:MonitoringPoint = new MonitoringPoint(569, "SH_PROBE_08A", "08A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_08B:MonitoringPoint = new MonitoringPoint(570, "SH_PROBE_08B", "08B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_08C:MonitoringPoint = new MonitoringPoint(571, "SH_PROBE_08C", "08C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_08D:MonitoringPoint = new MonitoringPoint(572, "SH_PROBE_08D", "08D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_08E:MonitoringPoint = new MonitoringPoint(573, "SH_PROBE_08E", "08E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_09A:MonitoringPoint = new MonitoringPoint(574, "SH_PROBE_09A", "09A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_09B:MonitoringPoint = new MonitoringPoint(575, "SH_PROBE_09B", "09B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_09C:MonitoringPoint = new MonitoringPoint(576, "SH_PROBE_09C", "09C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_09D:MonitoringPoint = new MonitoringPoint(577, "SH_PROBE_09D", "09D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_09E:MonitoringPoint = new MonitoringPoint(578, "SH_PROBE_09E", "09E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_10A:MonitoringPoint = new MonitoringPoint(579, "SH_PROBE_10A", "10A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_10B:MonitoringPoint = new MonitoringPoint(580, "SH_PROBE_10B", "10B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_10C:MonitoringPoint = new MonitoringPoint(581, "SH_PROBE_10C", "10C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_10D:MonitoringPoint = new MonitoringPoint(582, "SH_PROBE_10D", "10D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_10E:MonitoringPoint = new MonitoringPoint(583, "SH_PROBE_10E", "10E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_11A:MonitoringPoint = new MonitoringPoint(584, "SH_PROBE_11A", "11A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_11B:MonitoringPoint = new MonitoringPoint(585, "SH_PROBE_11B", "11B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_11C:MonitoringPoint = new MonitoringPoint(586, "SH_PROBE_11C", "11C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_11D:MonitoringPoint = new MonitoringPoint(587, "SH_PROBE_11D", "11D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_11E:MonitoringPoint = new MonitoringPoint(588, "SH_PROBE_11E", "11E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_12A:MonitoringPoint = new MonitoringPoint(589, "SH_PROBE_12A", "12A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_12B:MonitoringPoint = new MonitoringPoint(590, "SH_PROBE_12B", "12B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_12C:MonitoringPoint = new MonitoringPoint(591, "SH_PROBE_12C", "12C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_12D:MonitoringPoint = new MonitoringPoint(592, "SH_PROBE_12D", "12D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_12E:MonitoringPoint = new MonitoringPoint(593, "SH_PROBE_12E", "12E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_13A:MonitoringPoint = new MonitoringPoint(594, "SH_PROBE_13A", "13A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_13B:MonitoringPoint = new MonitoringPoint(595, "SH_PROBE_13B", "13B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_13C:MonitoringPoint = new MonitoringPoint(596, "SH_PROBE_13C", "13C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_13D:MonitoringPoint = new MonitoringPoint(597, "SH_PROBE_13D", "13D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_13E:MonitoringPoint = new MonitoringPoint(598, "SH_PROBE_13E", "13E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_14A:MonitoringPoint = new MonitoringPoint(599, "SH_PROBE_14A", "14A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_14B:MonitoringPoint = new MonitoringPoint(600, "SH_PROBE_14B", "14B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_14C:MonitoringPoint = new MonitoringPoint(601, "SH_PROBE_14C", "14C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_14D:MonitoringPoint = new MonitoringPoint(602, "SH_PROBE_14D", "14D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_14E:MonitoringPoint = new MonitoringPoint(603, "SH_PROBE_14E", "14E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_15A:MonitoringPoint = new MonitoringPoint(604, "SH_PROBE_15A", "15A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_15B:MonitoringPoint = new MonitoringPoint(605, "SH_PROBE_15B", "15B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_15C:MonitoringPoint = new MonitoringPoint(606, "SH_PROBE_15C", "15C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_15D:MonitoringPoint = new MonitoringPoint(607, "SH_PROBE_15D", "15D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_15E:MonitoringPoint = new MonitoringPoint(608, "SH_PROBE_15E", "15E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_16A:MonitoringPoint = new MonitoringPoint(609, "SH_PROBE_16A", "16A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_16B:MonitoringPoint = new MonitoringPoint(610, "SH_PROBE_16B", "16B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_16C:MonitoringPoint = new MonitoringPoint(611, "SH_PROBE_16C", "16C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_16D:MonitoringPoint = new MonitoringPoint(612, "SH_PROBE_16D", "16D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_16E:MonitoringPoint = new MonitoringPoint(613, "SH_PROBE_16E", "16E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_17A:MonitoringPoint = new MonitoringPoint(614, "SH_PROBE_17A", "17A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_17B:MonitoringPoint = new MonitoringPoint(615, "SH_PROBE_17B", "17B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_17C:MonitoringPoint = new MonitoringPoint(616, "SH_PROBE_17C", "17C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_17D:MonitoringPoint = new MonitoringPoint(617, "SH_PROBE_17D", "17D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_17E:MonitoringPoint = new MonitoringPoint(618, "SH_PROBE_17E", "17E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_18A:MonitoringPoint = new MonitoringPoint(619, "SH_PROBE_18A", "18A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_18B:MonitoringPoint = new MonitoringPoint(620, "SH_PROBE_18B", "18B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_18C:MonitoringPoint = new MonitoringPoint(621, "SH_PROBE_18C", "18C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_18D:MonitoringPoint = new MonitoringPoint(622, "SH_PROBE_18D", "18D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_18E:MonitoringPoint = new MonitoringPoint(623, "SH_PROBE_18E", "18E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_19A:MonitoringPoint = new MonitoringPoint(624, "SH_PROBE_19A", "19A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_19B:MonitoringPoint = new MonitoringPoint(625, "SH_PROBE_19B", "19B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_19C:MonitoringPoint = new MonitoringPoint(626, "SH_PROBE_19C", "19C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_19D:MonitoringPoint = new MonitoringPoint(627, "SH_PROBE_19D", "19D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_19E:MonitoringPoint = new MonitoringPoint(628, "SH_PROBE_19E", "19E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_20A:MonitoringPoint = new MonitoringPoint(629, "SH_PROBE_20A", "20A", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_20B:MonitoringPoint = new MonitoringPoint(630, "SH_PROBE_20B", "20B", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_20C:MonitoringPoint = new MonitoringPoint(631, "SH_PROBE_20C", "20C", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_20D:MonitoringPoint = new MonitoringPoint(632, "SH_PROBE_20D", "20D", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly SH_PROBE_20E:MonitoringPoint = new MonitoringPoint(633, "SH_PROBE_20E", "20E", Site.SHELDON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_01A:MonitoringPoint = new MonitoringPoint(634, "TC_PROBE_01A", "01A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_01B:MonitoringPoint = new MonitoringPoint(635, "TC_PROBE_01B", "01B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_02A:MonitoringPoint = new MonitoringPoint(636, "TC_PROBE_02A", "02A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_02B:MonitoringPoint = new MonitoringPoint(637, "TC_PROBE_02B", "02B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_02C:MonitoringPoint = new MonitoringPoint(638, "TC_PROBE_02C", "02C", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_03A:MonitoringPoint = new MonitoringPoint(639, "TC_PROBE_03A", "03A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_03B:MonitoringPoint = new MonitoringPoint(640, "TC_PROBE_03B", "03B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_04A:MonitoringPoint = new MonitoringPoint(641, "TC_PROBE_04A", "04A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_04B:MonitoringPoint = new MonitoringPoint(642, "TC_PROBE_04B", "04B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_05A:MonitoringPoint = new MonitoringPoint(643, "TC_PROBE_05A", "05A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_05B:MonitoringPoint = new MonitoringPoint(644, "TC_PROBE_05B", "05B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_05C:MonitoringPoint = new MonitoringPoint(645, "TC_PROBE_05C", "05C", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_05D:MonitoringPoint = new MonitoringPoint(646, "TC_PROBE_05D", "05D", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_06A:MonitoringPoint = new MonitoringPoint(647, "TC_PROBE_06A", "06A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_06B:MonitoringPoint = new MonitoringPoint(648, "TC_PROBE_06B", "06B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_06C:MonitoringPoint = new MonitoringPoint(649, "TC_PROBE_06C", "06C", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_07A:MonitoringPoint = new MonitoringPoint(650, "TC_PROBE_07A", "07A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_07B:MonitoringPoint = new MonitoringPoint(651, "TC_PROBE_07B", "07B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_07C:MonitoringPoint = new MonitoringPoint(652, "TC_PROBE_07C", "07C", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_07D:MonitoringPoint = new MonitoringPoint(653, "TC_PROBE_07D", "07D", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_08A:MonitoringPoint = new MonitoringPoint(654, "TC_PROBE_08A", "08A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_09A:MonitoringPoint = new MonitoringPoint(655, "TC_PROBE_09A", "09A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_09B:MonitoringPoint = new MonitoringPoint(656, "TC_PROBE_09B", "09B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_10A:MonitoringPoint = new MonitoringPoint(657, "TC_PROBE_10A", "10A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_11A:MonitoringPoint = new MonitoringPoint(658, "TC_PROBE_11A", "11A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_11B:MonitoringPoint = new MonitoringPoint(659, "TC_PROBE_11B", "11B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_11C:MonitoringPoint = new MonitoringPoint(660, "TC_PROBE_11C", "11C", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_11D:MonitoringPoint = new MonitoringPoint(661, "TC_PROBE_11D", "11D", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_12A:MonitoringPoint = new MonitoringPoint(662, "TC_PROBE_12A", "12A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_12B:MonitoringPoint = new MonitoringPoint(663, "TC_PROBE_12B", "12B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_13A:MonitoringPoint = new MonitoringPoint(664, "TC_PROBE_13A", "13A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_13B:MonitoringPoint = new MonitoringPoint(665, "TC_PROBE_13B", "13B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_13C:MonitoringPoint = new MonitoringPoint(666, "TC_PROBE_13C", "13C", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_13D:MonitoringPoint = new MonitoringPoint(667, "TC_PROBE_13D", "13D", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_14A:MonitoringPoint = new MonitoringPoint(668, "TC_PROBE_14A", "14A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_14B:MonitoringPoint = new MonitoringPoint(669, "TC_PROBE_14B", "14B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_14C:MonitoringPoint = new MonitoringPoint(670, "TC_PROBE_14C", "14C", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_15A:MonitoringPoint = new MonitoringPoint(671, "TC_PROBE_15A", "15A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_15B:MonitoringPoint = new MonitoringPoint(672, "TC_PROBE_15B", "15B", Site.TOYON, MonitoringPointType.PROBE);
	static readonly TC_PROBE_16A:MonitoringPoint = new MonitoringPoint(673, "TC_PROBE_16A", "16A", Site.TOYON, MonitoringPointType.PROBE);
	static readonly CL_STORMWATER_ENTRANCE:MonitoringPoint = new MonitoringPoint(674, "CL_STORMWATER_ENTRANCE", "Entrance", Site.CLARTS, MonitoringPointType.STORMWATER);
	static readonly CL_STORMWATER_EXIT:MonitoringPoint = new MonitoringPoint(675, "CL_STORMWATER_EXIT", "Exit", Site.CLARTS, MonitoringPointType.STORMWATER);
	static readonly LC_STORMWATER_A_CYN:MonitoringPoint = new MonitoringPoint(676, "LC_STORMWATER_A_CYN", "A-Cyn", Site.LOPEZ, MonitoringPointType.STORMWATER);
	static readonly LC_STORMWATER_B_CYN:MonitoringPoint = new MonitoringPoint(677, "LC_STORMWATER_B_CYN", "B-Cyn", Site.LOPEZ, MonitoringPointType.STORMWATER);
	static readonly LC_STORMWATER_B4_BASIN_INLET:MonitoringPoint = new MonitoringPoint(678, "LC_STORMWATER_B4_BASIN_INLET", "B4 Basin inlet", Site.LOPEZ, MonitoringPointType.STORMWATER);
	static readonly LC_STORMWATER_C_CYN:MonitoringPoint = new MonitoringPoint(679, "LC_STORMWATER_C_CYN", "C-Cyn", Site.LOPEZ, MonitoringPointType.STORMWATER);
	static readonly LC_STORMWATER_CLARIFIER_INLET:MonitoringPoint = new MonitoringPoint(680, "LC_STORMWATER_CLARIFIER_INLET", "Clarifier inlet", Site.LOPEZ, MonitoringPointType.STORMWATER);

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;
	readonly site:Site;
	readonly type:MonitoringPointType;

	private constructor(ordinal:number, constantName:string, name:string, site:Site, type:MonitoringPointType) {
		this.ordinal = ordinal;
		this.constantName = constantName;
		this.name = name;
		this.site = site;
		this.type = type;
	}

	static values():MonitoringPoint[] {
		return [
			MonitoringPoint.LC_AMBIENT_1A,
			MonitoringPoint.LC_AMBIENT_2B,
			MonitoringPoint.LC_AMBIENT_3B,
			MonitoringPoint.LC_AMBIENT_4A,
			MonitoringPoint.LC_AMBIENT_4B,
			MonitoringPoint.LC_AMBIENT_5A,
			MonitoringPoint.LC_AMBIENT_5B,
			MonitoringPoint.LC_AMBIENT_6A,
			MonitoringPoint.LC_AMBIENT_6B,
			MonitoringPoint.SH_AMBIENT_01N,
			MonitoringPoint.SH_AMBIENT_02S,
			MonitoringPoint.TC_AMBIENT_SB,
			MonitoringPoint.TC_AMBIENT_ST,
			MonitoringPoint.GP_BIOFILTER_A_IN,
			MonitoringPoint.GP_BIOFILTER_A_OUT,
			MonitoringPoint.GP_BIOFILTER_B_IN,
			MonitoringPoint.GP_BIOFILTER_B_OUT,
			MonitoringPoint.BC_GRID_A1,
			MonitoringPoint.BC_GRID_A2,
			MonitoringPoint.BC_GRID_A3,
			MonitoringPoint.BC_GRID_A4,
			MonitoringPoint.BC_GRID_A5,
			MonitoringPoint.BC_GRID_A6,
			MonitoringPoint.BC_GRID_A7,
			MonitoringPoint.BC_GRID_A8,
			MonitoringPoint.BC_GRID_A9,
			MonitoringPoint.BC_GRID_A10,
			MonitoringPoint.BC_GRID_A11,
			MonitoringPoint.BC_GRID_A12,
			MonitoringPoint.BC_GRID_B1,
			MonitoringPoint.BC_GRID_B2,
			MonitoringPoint.BC_GRID_B3,
			MonitoringPoint.BC_GRID_B4,
			MonitoringPoint.GA_GRID_1,
			MonitoringPoint.GA_GRID_2,
			MonitoringPoint.GA_GRID_3,
			MonitoringPoint.GA_GRID_4,
			MonitoringPoint.GA_GRID_5,
			MonitoringPoint.GA_GRID_6,
			MonitoringPoint.GA_GRID_7,
			MonitoringPoint.GA_GRID_8,
			MonitoringPoint.GA_GRID_9,
			MonitoringPoint.GA_GRID_10,
			MonitoringPoint.GA_GRID_11,
			MonitoringPoint.GA_GRID_12,
			MonitoringPoint.LC_GRID_1,
			MonitoringPoint.LC_GRID_2,
			MonitoringPoint.LC_GRID_3,
			MonitoringPoint.LC_GRID_4,
			MonitoringPoint.LC_GRID_5,
			MonitoringPoint.LC_GRID_6,
			MonitoringPoint.LC_GRID_7,
			MonitoringPoint.LC_GRID_8,
			MonitoringPoint.LC_GRID_9,
			MonitoringPoint.LC_GRID_10,
			MonitoringPoint.LC_GRID_11,
			MonitoringPoint.LC_GRID_12,
			MonitoringPoint.LC_GRID_13,
			MonitoringPoint.LC_GRID_14,
			MonitoringPoint.LC_GRID_15,
			MonitoringPoint.LC_GRID_16,
			MonitoringPoint.LC_GRID_17,
			MonitoringPoint.LC_GRID_18,
			MonitoringPoint.LC_GRID_19,
			MonitoringPoint.LC_GRID_20,
			MonitoringPoint.LC_GRID_21,
			MonitoringPoint.LC_GRID_22,
			MonitoringPoint.LC_GRID_23,
			MonitoringPoint.LC_GRID_24,
			MonitoringPoint.LC_GRID_25,
			MonitoringPoint.LC_GRID_26,
			MonitoringPoint.LC_GRID_27,
			MonitoringPoint.LC_GRID_28,
			MonitoringPoint.LC_GRID_29,
			MonitoringPoint.LC_GRID_30,
			MonitoringPoint.LC_GRID_31,
			MonitoringPoint.LC_GRID_32,
			MonitoringPoint.LC_GRID_33,
			MonitoringPoint.LC_GRID_34,
			MonitoringPoint.LC_GRID_35,
			MonitoringPoint.LC_GRID_36,
			MonitoringPoint.LC_GRID_37,
			MonitoringPoint.LC_GRID_38,
			MonitoringPoint.LC_GRID_39,
			MonitoringPoint.LC_GRID_40,
			MonitoringPoint.LC_GRID_41,
			MonitoringPoint.LC_GRID_42,
			MonitoringPoint.LC_GRID_43,
			MonitoringPoint.LC_GRID_44,
			MonitoringPoint.LC_GRID_45,
			MonitoringPoint.LC_GRID_46,
			MonitoringPoint.LC_GRID_47,
			MonitoringPoint.LC_GRID_48,
			MonitoringPoint.LC_GRID_49,
			MonitoringPoint.LC_GRID_50,
			MonitoringPoint.LC_GRID_51,
			MonitoringPoint.LC_GRID_52,
			MonitoringPoint.LC_GRID_53,
			MonitoringPoint.LC_GRID_54,
			MonitoringPoint.LC_GRID_55,
			MonitoringPoint.LC_GRID_56,
			MonitoringPoint.LC_GRID_57,
			MonitoringPoint.LC_GRID_58,
			MonitoringPoint.LC_GRID_59,
			MonitoringPoint.LC_GRID_60,
			MonitoringPoint.LC_GRID_61,
			MonitoringPoint.LC_GRID_62,
			MonitoringPoint.LC_GRID_63,
			MonitoringPoint.LC_GRID_64,
			MonitoringPoint.LC_GRID_65,
			MonitoringPoint.LC_GRID_66,
			MonitoringPoint.LC_GRID_67,
			MonitoringPoint.LC_GRID_68,
			MonitoringPoint.LC_GRID_69,
			MonitoringPoint.LC_GRID_70,
			MonitoringPoint.LC_GRID_71,
			MonitoringPoint.LC_GRID_72,
			MonitoringPoint.LC_GRID_73,
			MonitoringPoint.LC_GRID_74,
			MonitoringPoint.LC_GRID_75,
			MonitoringPoint.LC_GRID_76,
			MonitoringPoint.LC_GRID_77,
			MonitoringPoint.LC_GRID_78,
			MonitoringPoint.LC_GRID_79,
			MonitoringPoint.LC_GRID_80,
			MonitoringPoint.LC_GRID_81,
			MonitoringPoint.LC_GRID_82,
			MonitoringPoint.LC_GRID_83,
			MonitoringPoint.LC_GRID_84,
			MonitoringPoint.LC_GRID_85,
			MonitoringPoint.LC_GRID_86,
			MonitoringPoint.LC_GRID_87,
			MonitoringPoint.LC_GRID_88,
			MonitoringPoint.LC_GRID_89,
			MonitoringPoint.LC_GRID_90,
			MonitoringPoint.LC_GRID_91,
			MonitoringPoint.LC_GRID_92,
			MonitoringPoint.LC_GRID_93,
			MonitoringPoint.LC_GRID_94,
			MonitoringPoint.LC_GRID_95,
			MonitoringPoint.LC_GRID_96,
			MonitoringPoint.LC_GRID_97,
			MonitoringPoint.LC_GRID_98,
			MonitoringPoint.LC_GRID_99,
			MonitoringPoint.LC_GRID_100,
			MonitoringPoint.LC_GRID_101,
			MonitoringPoint.LC_GRID_102,
			MonitoringPoint.LC_GRID_103,
			MonitoringPoint.LC_GRID_104,
			MonitoringPoint.LC_GRID_105,
			MonitoringPoint.LC_GRID_106,
			MonitoringPoint.LC_GRID_107,
			MonitoringPoint.LC_GRID_108,
			MonitoringPoint.LC_GRID_109,
			MonitoringPoint.LC_GRID_110,
			MonitoringPoint.LC_GRID_111,
			MonitoringPoint.LC_GRID_112,
			MonitoringPoint.LC_GRID_113,
			MonitoringPoint.LC_GRID_114,
			MonitoringPoint.LC_GRID_115,
			MonitoringPoint.LC_GRID_116,
			MonitoringPoint.LC_GRID_117,
			MonitoringPoint.LC_GRID_118,
			MonitoringPoint.LC_GRID_119,
			MonitoringPoint.LC_GRID_120,
			MonitoringPoint.LC_GRID_121,
			MonitoringPoint.LC_GRID_122,
			MonitoringPoint.LC_GRID_123,
			MonitoringPoint.LC_GRID_124,
			MonitoringPoint.LC_GRID_125,
			MonitoringPoint.LC_GRID_126,
			MonitoringPoint.LC_GRID_127,
			MonitoringPoint.LC_GRID_128,
			MonitoringPoint.LC_GRID_129,
			MonitoringPoint.LC_GRID_130,
			MonitoringPoint.SH_GRID_1,
			MonitoringPoint.SH_GRID_2,
			MonitoringPoint.SH_GRID_3,
			MonitoringPoint.SH_GRID_4,
			MonitoringPoint.SH_GRID_5,
			MonitoringPoint.SH_GRID_6,
			MonitoringPoint.SH_GRID_7,
			MonitoringPoint.SH_GRID_8,
			MonitoringPoint.SH_GRID_9,
			MonitoringPoint.SH_GRID_10,
			MonitoringPoint.SH_GRID_11,
			MonitoringPoint.SH_GRID_12,
			MonitoringPoint.SH_GRID_13,
			MonitoringPoint.SH_GRID_14,
			MonitoringPoint.SH_GRID_15,
			MonitoringPoint.SH_GRID_16,
			MonitoringPoint.SH_GRID_17,
			MonitoringPoint.SH_GRID_18,
			MonitoringPoint.SH_GRID_19,
			MonitoringPoint.SH_GRID_20,
			MonitoringPoint.SH_GRID_21,
			MonitoringPoint.SH_GRID_22,
			MonitoringPoint.SH_GRID_23,
			MonitoringPoint.SH_GRID_24,
			MonitoringPoint.SH_GRID_25,
			MonitoringPoint.SH_GRID_26,
			MonitoringPoint.SH_GRID_27,
			MonitoringPoint.SH_GRID_28,
			MonitoringPoint.SH_GRID_29,
			MonitoringPoint.SH_GRID_30,
			MonitoringPoint.SH_GRID_31,
			MonitoringPoint.SH_GRID_32,
			MonitoringPoint.SH_GRID_33,
			MonitoringPoint.SH_GRID_34,
			MonitoringPoint.SH_GRID_35,
			MonitoringPoint.SH_GRID_36,
			MonitoringPoint.SH_GRID_37,
			MonitoringPoint.SH_GRID_38,
			MonitoringPoint.SH_GRID_39,
			MonitoringPoint.SH_GRID_40,
			MonitoringPoint.TC_GRID_1,
			MonitoringPoint.TC_GRID_2,
			MonitoringPoint.TC_GRID_3,
			MonitoringPoint.TC_GRID_4,
			MonitoringPoint.TC_GRID_5,
			MonitoringPoint.TC_GRID_6,
			MonitoringPoint.TC_GRID_7,
			MonitoringPoint.TC_GRID_8,
			MonitoringPoint.TC_GRID_9,
			MonitoringPoint.TC_GRID_10,
			MonitoringPoint.TC_GRID_11,
			MonitoringPoint.TC_GRID_12,
			MonitoringPoint.TC_GRID_13,
			MonitoringPoint.TC_GRID_14,
			MonitoringPoint.TC_GRID_15,
			MonitoringPoint.TC_GRID_16,
			MonitoringPoint.TC_GRID_17,
			MonitoringPoint.TC_GRID_18,
			MonitoringPoint.TC_GRID_19,
			MonitoringPoint.TC_GRID_20,
			MonitoringPoint.TC_GRID_21,
			MonitoringPoint.TC_GRID_22,
			MonitoringPoint.TC_GRID_23,
			MonitoringPoint.TC_GRID_24,
			MonitoringPoint.TC_GRID_25,
			MonitoringPoint.TC_GRID_26,
			MonitoringPoint.TC_GRID_27,
			MonitoringPoint.TC_GRID_28,
			MonitoringPoint.TC_GRID_29,
			MonitoringPoint.TC_GRID_30,
			MonitoringPoint.TC_GRID_31,
			MonitoringPoint.TC_GRID_32,
			MonitoringPoint.TC_GRID_33,
			MonitoringPoint.TC_GRID_34,
			MonitoringPoint.TC_GRID_35,
			MonitoringPoint.TC_GRID_36,
			MonitoringPoint.TC_GRID_37,
			MonitoringPoint.TC_GRID_38,
			MonitoringPoint.TC_GRID_39,
			MonitoringPoint.TC_GRID_40,
			MonitoringPoint.TC_GRID_41,
			MonitoringPoint.TC_GRID_42,
			MonitoringPoint.TC_GRID_43,
			MonitoringPoint.TC_GRID_44,
			MonitoringPoint.TC_GRID_45,
			MonitoringPoint.TC_GRID_46,
			MonitoringPoint.TC_GRID_47,
			MonitoringPoint.TC_GRID_48,
			MonitoringPoint.TC_GRID_49,
			MonitoringPoint.TC_GRID_50,
			MonitoringPoint.TC_GRID_51,
			MonitoringPoint.TC_GRID_52,
			MonitoringPoint.TC_GRID_53,
			MonitoringPoint.TC_GRID_54,
			MonitoringPoint.TC_GRID_55,
			MonitoringPoint.TC_GRID_56,
			MonitoringPoint.TC_GRID_57,
			MonitoringPoint.TC_GRID_58,
			MonitoringPoint.TC_GRID_59,
			MonitoringPoint.TC_GRID_60,
			MonitoringPoint.TC_GRID_61,
			MonitoringPoint.TC_GRID_62,
			MonitoringPoint.TC_GRID_63,
			MonitoringPoint.TC_GRID_64,
			MonitoringPoint.TC_GRID_65,
			MonitoringPoint.TC_GRID_66,
			MonitoringPoint.BC_GROUNDWATER_BC_01,
			MonitoringPoint.BC_GROUNDWATER_BC_02,
			MonitoringPoint.BC_GROUNDWATER_BC_02A,
			MonitoringPoint.BC_GROUNDWATER_BC_03,
			MonitoringPoint.BC_GROUNDWATER_BC_04,
			MonitoringPoint.BC_GROUNDWATER_BC_L,
			MonitoringPoint.BC_GROUNDWATER_BC_LW,
			MonitoringPoint.BR_GROUNDWATER_MW_1,
			MonitoringPoint.BR_GROUNDWATER_MW_2,
			MonitoringPoint.BR_GROUNDWATER_MW_3,
			MonitoringPoint.GA_GROUNDWATER_LW_1,
			MonitoringPoint.GA_GROUNDWATER_LW_2,
			MonitoringPoint.GA_GROUNDWATER_MW_1,
			MonitoringPoint.GA_GROUNDWATER_MW_2,
			MonitoringPoint.GA_GROUNDWATER_MW_3,
			MonitoringPoint.GA_GROUNDWATER_MW_A,
			MonitoringPoint.GA_GROUNDWATER_MW_B,
			MonitoringPoint.GA_GROUNDWATER_MW_C,
			MonitoringPoint.GA_GROUNDWATER_MW_D,
			MonitoringPoint.LC_GROUNDWATER_MW92_1,
			MonitoringPoint.LC_GROUNDWATER_MW92_2,
			MonitoringPoint.LC_GROUNDWATER_MW92_3,
			MonitoringPoint.LC_GROUNDWATER_MW93_1,
			MonitoringPoint.LC_GROUNDWATER_MW93_2,
			MonitoringPoint.LC_GROUNDWATER_MW95_1,
			MonitoringPoint.LC_GROUNDWATER_MW95_2,
			MonitoringPoint.LC_GROUNDWATER_MW95_3,
			MonitoringPoint.LC_GROUNDWATER_MW95_4,
			MonitoringPoint.LC_GROUNDWATER_MW95_5,
			MonitoringPoint.LC_GROUNDWATER_MW95_6,
			MonitoringPoint.SH_GROUNDWATER_4897A,
			MonitoringPoint.SH_GROUNDWATER_4897B,
			MonitoringPoint.SH_GROUNDWATER_4897C,
			MonitoringPoint.SH_GROUNDWATER_4897D,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_1,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_2,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_3,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_4,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_5,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_6,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_7,
			MonitoringPoint.SH_GROUNDWATER_IT_SA_8,
			MonitoringPoint.SH_GROUNDWATER_SA_1A,
			MonitoringPoint.SH_GROUNDWATER_SA_3A,
			MonitoringPoint.SH_GROUNDWATER_SA_8A,
			MonitoringPoint.SH_GROUNDWATER_SA_LW,
			MonitoringPoint.TC_GROUNDWATER_MW_1,
			MonitoringPoint.TC_GROUNDWATER_MW_2,
			MonitoringPoint.TC_GROUNDWATER_MW_3,
			MonitoringPoint.TC_GROUNDWATER_MW_4,
			MonitoringPoint.TC_GROUNDWATER_MW_4A,
			MonitoringPoint.TC_GROUNDWATER_MW_5,
			MonitoringPoint.TC_GROUNDWATER_MW_6,
			MonitoringPoint.TC_GROUNDWATER_MW_7,
			MonitoringPoint.TC_GROUNDWATER_MW_8,
			MonitoringPoint.TC_GROUNDWATER_MW_8A,
			MonitoringPoint.TC_GROUNDWATER_MW_9,
			MonitoringPoint.TC_GROUNDWATER_TII_1,
			MonitoringPoint.TC_GROUNDWATER_TII_2,
			MonitoringPoint.TC_GROUNDWATER_TII_3,
			MonitoringPoint.GA_LFG_IN,
			MonitoringPoint.GA_LFG_MID,
			MonitoringPoint.GA_LFG_OUT,
			MonitoringPoint.LC_LFG_CO,
			MonitoringPoint.LC_LFG_F1,
			MonitoringPoint.LC_LFG_F2,
			MonitoringPoint.LC_LFG_F3,
			MonitoringPoint.LC_LFG_F4,
			MonitoringPoint.LC_LFG_F5,
			MonitoringPoint.LC_LFG_F6,
			MonitoringPoint.LC_LFG_F7,
			MonitoringPoint.LC_LFG_F8,
			MonitoringPoint.LC_LFG_F9,
			MonitoringPoint.SH_LFG_F1,
			MonitoringPoint.SH_LFG_F2,
			MonitoringPoint.TC_LFG_CO,
			MonitoringPoint.TC_LFG_FL,
			MonitoringPoint.LC_LEACHATE_AB,
			MonitoringPoint.LC_LEACHATE_C,
			MonitoringPoint.TC_LEACHATE_LB_1N,
			MonitoringPoint.TC_LEACHATE_LB_1S,
			MonitoringPoint.GP_PILE_A1,
			MonitoringPoint.GP_PILE_A2,
			MonitoringPoint.GP_PILE_A3,
			MonitoringPoint.GP_PILE_A4,
			MonitoringPoint.GP_PILE_A5,
			MonitoringPoint.GP_PILE_A6,
			MonitoringPoint.GP_PILE_A7,
			MonitoringPoint.GP_PILE_A8,
			MonitoringPoint.GP_PILE_A9,
			MonitoringPoint.GP_PILE_B1,
			MonitoringPoint.GP_PILE_B2,
			MonitoringPoint.GP_PILE_B3,
			MonitoringPoint.GP_PILE_B4,
			MonitoringPoint.GP_PILE_B5,
			MonitoringPoint.GP_PILE_B6,
			MonitoringPoint.GP_PILE_B7,
			MonitoringPoint.GP_PILE_B8,
			MonitoringPoint.GP_PILE_B9,
			MonitoringPoint.LC_PILE_1,
			MonitoringPoint.LC_PILE_2,
			MonitoringPoint.LC_PILE_3,
			MonitoringPoint.LC_PILE_4,
			MonitoringPoint.LC_PILE_5,
			MonitoringPoint.LC_PILE_6,
			MonitoringPoint.LC_PILE_7,
			MonitoringPoint.LC_PILE_8,
			MonitoringPoint.LC_PILE_9,
			MonitoringPoint.LC_PILE_10,
			MonitoringPoint.LC_PILE_11,
			MonitoringPoint.LC_PILE_12,
			MonitoringPoint.LC_PILE_13,
			MonitoringPoint.LC_PILE_14,
			MonitoringPoint.LC_PILE_15,
			MonitoringPoint.LC_PILE_16,
			MonitoringPoint.LC_PILE_17,
			MonitoringPoint.LC_PILE_18,
			MonitoringPoint.LC_PILE_19,
			MonitoringPoint.LC_PILE_20,
			MonitoringPoint.LC_PILE_21,
			MonitoringPoint.LC_PILE_22,
			MonitoringPoint.LC_PILE_23,
			MonitoringPoint.LC_PILE_24,
			MonitoringPoint.BC_PROBE_01A,
			MonitoringPoint.BC_PROBE_02A,
			MonitoringPoint.BC_PROBE_02B,
			MonitoringPoint.BC_PROBE_02C,
			MonitoringPoint.BC_PROBE_03A,
			MonitoringPoint.BC_PROBE_03B,
			MonitoringPoint.BC_PROBE_03C,
			MonitoringPoint.BC_PROBE_03D,
			MonitoringPoint.BC_PROBE_04A,
			MonitoringPoint.BC_PROBE_04B,
			MonitoringPoint.BC_PROBE_04C,
			MonitoringPoint.BC_PROBE_04D,
			MonitoringPoint.BC_PROBE_05A,
			MonitoringPoint.BC_PROBE_05B,
			MonitoringPoint.BC_PROBE_05C,
			MonitoringPoint.BC_PROBE_05D,
			MonitoringPoint.BC_PROBE_06A,
			MonitoringPoint.BC_PROBE_06B,
			MonitoringPoint.BC_PROBE_06C,
			MonitoringPoint.BC_PROBE_07A,
			MonitoringPoint.BC_PROBE_07B,
			MonitoringPoint.BC_PROBE_07C,
			MonitoringPoint.BC_PROBE_08A,
			MonitoringPoint.BC_PROBE_08B,
			MonitoringPoint.BC_PROBE_08C,
			MonitoringPoint.BC_PROBE_09A,
			MonitoringPoint.BC_PROBE_09B,
			MonitoringPoint.BC_PROBE_09C,
			MonitoringPoint.GA_PROBE_01A,
			MonitoringPoint.GA_PROBE_01B,
			MonitoringPoint.GA_PROBE_01C,
			MonitoringPoint.GA_PROBE_02A,
			MonitoringPoint.GA_PROBE_03A,
			MonitoringPoint.GA_PROBE_04A,
			MonitoringPoint.GA_PROBE_05A,
			MonitoringPoint.GA_PROBE_06A,
			MonitoringPoint.GA_PROBE_06B,
			MonitoringPoint.GA_PROBE_06C,
			MonitoringPoint.GA_PROBE_07A,
			MonitoringPoint.GA_PROBE_07B,
			MonitoringPoint.GA_PROBE_07C,
			MonitoringPoint.GA_PROBE_08A,
			MonitoringPoint.GA_PROBE_08B,
			MonitoringPoint.GA_PROBE_08C,
			MonitoringPoint.GA_PROBE_09A,
			MonitoringPoint.GA_PROBE_09B,
			MonitoringPoint.GA_PROBE_09C,
			MonitoringPoint.GA_PROBE_10A,
			MonitoringPoint.GA_PROBE_10B,
			MonitoringPoint.GA_PROBE_10C,
			MonitoringPoint.GA_PROBE_11A,
			MonitoringPoint.GA_PROBE_11B,
			MonitoringPoint.GA_PROBE_11C,
			MonitoringPoint.GA_PROBE_12A,
			MonitoringPoint.GA_PROBE_12B,
			MonitoringPoint.GA_PROBE_12C,
			MonitoringPoint.LC_PROBE_01A,
			MonitoringPoint.LC_PROBE_02A,
			MonitoringPoint.LC_PROBE_03A,
			MonitoringPoint.LC_PROBE_04A,
			MonitoringPoint.LC_PROBE_05A,
			MonitoringPoint.LC_PROBE_06A,
			MonitoringPoint.LC_PROBE_07A,
			MonitoringPoint.LC_PROBE_08A,
			MonitoringPoint.LC_PROBE_09A,
			MonitoringPoint.LC_PROBE_10A,
			MonitoringPoint.LC_PROBE_10B,
			MonitoringPoint.LC_PROBE_10C,
			MonitoringPoint.LC_PROBE_10D,
			MonitoringPoint.LC_PROBE_13A,
			MonitoringPoint.LC_PROBE_14A,
			MonitoringPoint.LC_PROBE_15A,
			MonitoringPoint.LC_PROBE_16A,
			MonitoringPoint.LC_PROBE_17A,
			MonitoringPoint.LC_PROBE_18A,
			MonitoringPoint.LC_PROBE_19A,
			MonitoringPoint.LC_PROBE_20A,
			MonitoringPoint.LC_PROBE_21A,
			MonitoringPoint.LC_PROBE_23A,
			MonitoringPoint.LC_PROBE_24A,
			MonitoringPoint.LC_PROBE_24B,
			MonitoringPoint.LC_PROBE_24C,
			MonitoringPoint.LC_PROBE_24D,
			MonitoringPoint.LC_PROBE_27A,
			MonitoringPoint.LC_PROBE_28A,
			MonitoringPoint.LC_PROBE_29A,
			MonitoringPoint.LC_PROBE_30A,
			MonitoringPoint.LC_PROBE_31A,
			MonitoringPoint.LC_PROBE_32A,
			MonitoringPoint.PH_PROBE_01A,
			MonitoringPoint.PH_PROBE_01B,
			MonitoringPoint.PH_PROBE_01C,
			MonitoringPoint.PH_PROBE_02A,
			MonitoringPoint.PH_PROBE_02B,
			MonitoringPoint.PH_PROBE_02C,
			MonitoringPoint.PH_PROBE_03A,
			MonitoringPoint.PH_PROBE_03B,
			MonitoringPoint.PH_PROBE_03C,
			MonitoringPoint.PH_PROBE_04A,
			MonitoringPoint.PH_PROBE_04B,
			MonitoringPoint.PH_PROBE_04C,
			MonitoringPoint.PH_PROBE_05A,
			MonitoringPoint.PH_PROBE_05B,
			MonitoringPoint.PH_PROBE_05C,
			MonitoringPoint.PH_PROBE_06A,
			MonitoringPoint.PH_PROBE_06B,
			MonitoringPoint.PH_PROBE_07A,
			MonitoringPoint.PH_PROBE_07B,
			MonitoringPoint.PH_PROBE_08A,
			MonitoringPoint.PH_PROBE_08B,
			MonitoringPoint.PH_PROBE_09A,
			MonitoringPoint.PH_PROBE_09B,
			MonitoringPoint.PH_PROBE_10A,
			MonitoringPoint.PH_PROBE_10B,
			MonitoringPoint.PH_PROBE_11A,
			MonitoringPoint.PH_PROBE_11B,
			MonitoringPoint.PH_PROBE_12A,
			MonitoringPoint.PH_PROBE_12B,
			MonitoringPoint.PH_PROBE_13A,
			MonitoringPoint.PH_PROBE_13B,
			MonitoringPoint.PH_PROBE_14A,
			MonitoringPoint.PH_PROBE_14B,
			MonitoringPoint.PH_PROBE_15A,
			MonitoringPoint.PH_PROBE_15B,
			MonitoringPoint.PH_PROBE_16A,
			MonitoringPoint.PH_PROBE_16B,
			MonitoringPoint.PH_PROBE_17A,
			MonitoringPoint.PH_PROBE_17B,
			MonitoringPoint.PH_PROBE_18A,
			MonitoringPoint.PH_PROBE_18B,
			MonitoringPoint.SH_PROBE_01A,
			MonitoringPoint.SH_PROBE_01B,
			MonitoringPoint.SH_PROBE_01C,
			MonitoringPoint.SH_PROBE_01D,
			MonitoringPoint.SH_PROBE_01E,
			MonitoringPoint.SH_PROBE_02A,
			MonitoringPoint.SH_PROBE_02B,
			MonitoringPoint.SH_PROBE_02C,
			MonitoringPoint.SH_PROBE_02D,
			MonitoringPoint.SH_PROBE_02E,
			MonitoringPoint.SH_PROBE_03A,
			MonitoringPoint.SH_PROBE_03B,
			MonitoringPoint.SH_PROBE_03C,
			MonitoringPoint.SH_PROBE_03D,
			MonitoringPoint.SH_PROBE_03E,
			MonitoringPoint.SH_PROBE_04A,
			MonitoringPoint.SH_PROBE_04B,
			MonitoringPoint.SH_PROBE_04C,
			MonitoringPoint.SH_PROBE_04D,
			MonitoringPoint.SH_PROBE_04E,
			MonitoringPoint.SH_PROBE_05A,
			MonitoringPoint.SH_PROBE_05B,
			MonitoringPoint.SH_PROBE_05C,
			MonitoringPoint.SH_PROBE_05D,
			MonitoringPoint.SH_PROBE_05E,
			MonitoringPoint.SH_PROBE_06A,
			MonitoringPoint.SH_PROBE_06B,
			MonitoringPoint.SH_PROBE_06C,
			MonitoringPoint.SH_PROBE_06D,
			MonitoringPoint.SH_PROBE_06E,
			MonitoringPoint.SH_PROBE_07A,
			MonitoringPoint.SH_PROBE_07B,
			MonitoringPoint.SH_PROBE_07C,
			MonitoringPoint.SH_PROBE_07D,
			MonitoringPoint.SH_PROBE_07E,
			MonitoringPoint.SH_PROBE_08A,
			MonitoringPoint.SH_PROBE_08B,
			MonitoringPoint.SH_PROBE_08C,
			MonitoringPoint.SH_PROBE_08D,
			MonitoringPoint.SH_PROBE_08E,
			MonitoringPoint.SH_PROBE_09A,
			MonitoringPoint.SH_PROBE_09B,
			MonitoringPoint.SH_PROBE_09C,
			MonitoringPoint.SH_PROBE_09D,
			MonitoringPoint.SH_PROBE_09E,
			MonitoringPoint.SH_PROBE_10A,
			MonitoringPoint.SH_PROBE_10B,
			MonitoringPoint.SH_PROBE_10C,
			MonitoringPoint.SH_PROBE_10D,
			MonitoringPoint.SH_PROBE_10E,
			MonitoringPoint.SH_PROBE_11A,
			MonitoringPoint.SH_PROBE_11B,
			MonitoringPoint.SH_PROBE_11C,
			MonitoringPoint.SH_PROBE_11D,
			MonitoringPoint.SH_PROBE_11E,
			MonitoringPoint.SH_PROBE_12A,
			MonitoringPoint.SH_PROBE_12B,
			MonitoringPoint.SH_PROBE_12C,
			MonitoringPoint.SH_PROBE_12D,
			MonitoringPoint.SH_PROBE_12E,
			MonitoringPoint.SH_PROBE_13A,
			MonitoringPoint.SH_PROBE_13B,
			MonitoringPoint.SH_PROBE_13C,
			MonitoringPoint.SH_PROBE_13D,
			MonitoringPoint.SH_PROBE_13E,
			MonitoringPoint.SH_PROBE_14A,
			MonitoringPoint.SH_PROBE_14B,
			MonitoringPoint.SH_PROBE_14C,
			MonitoringPoint.SH_PROBE_14D,
			MonitoringPoint.SH_PROBE_14E,
			MonitoringPoint.SH_PROBE_15A,
			MonitoringPoint.SH_PROBE_15B,
			MonitoringPoint.SH_PROBE_15C,
			MonitoringPoint.SH_PROBE_15D,
			MonitoringPoint.SH_PROBE_15E,
			MonitoringPoint.SH_PROBE_16A,
			MonitoringPoint.SH_PROBE_16B,
			MonitoringPoint.SH_PROBE_16C,
			MonitoringPoint.SH_PROBE_16D,
			MonitoringPoint.SH_PROBE_16E,
			MonitoringPoint.SH_PROBE_17A,
			MonitoringPoint.SH_PROBE_17B,
			MonitoringPoint.SH_PROBE_17C,
			MonitoringPoint.SH_PROBE_17D,
			MonitoringPoint.SH_PROBE_17E,
			MonitoringPoint.SH_PROBE_18A,
			MonitoringPoint.SH_PROBE_18B,
			MonitoringPoint.SH_PROBE_18C,
			MonitoringPoint.SH_PROBE_18D,
			MonitoringPoint.SH_PROBE_18E,
			MonitoringPoint.SH_PROBE_19A,
			MonitoringPoint.SH_PROBE_19B,
			MonitoringPoint.SH_PROBE_19C,
			MonitoringPoint.SH_PROBE_19D,
			MonitoringPoint.SH_PROBE_19E,
			MonitoringPoint.SH_PROBE_20A,
			MonitoringPoint.SH_PROBE_20B,
			MonitoringPoint.SH_PROBE_20C,
			MonitoringPoint.SH_PROBE_20D,
			MonitoringPoint.SH_PROBE_20E,
			MonitoringPoint.TC_PROBE_01A,
			MonitoringPoint.TC_PROBE_01B,
			MonitoringPoint.TC_PROBE_02A,
			MonitoringPoint.TC_PROBE_02B,
			MonitoringPoint.TC_PROBE_02C,
			MonitoringPoint.TC_PROBE_03A,
			MonitoringPoint.TC_PROBE_03B,
			MonitoringPoint.TC_PROBE_04A,
			MonitoringPoint.TC_PROBE_04B,
			MonitoringPoint.TC_PROBE_05A,
			MonitoringPoint.TC_PROBE_05B,
			MonitoringPoint.TC_PROBE_05C,
			MonitoringPoint.TC_PROBE_05D,
			MonitoringPoint.TC_PROBE_06A,
			MonitoringPoint.TC_PROBE_06B,
			MonitoringPoint.TC_PROBE_06C,
			MonitoringPoint.TC_PROBE_07A,
			MonitoringPoint.TC_PROBE_07B,
			MonitoringPoint.TC_PROBE_07C,
			MonitoringPoint.TC_PROBE_07D,
			MonitoringPoint.TC_PROBE_08A,
			MonitoringPoint.TC_PROBE_09A,
			MonitoringPoint.TC_PROBE_09B,
			MonitoringPoint.TC_PROBE_10A,
			MonitoringPoint.TC_PROBE_11A,
			MonitoringPoint.TC_PROBE_11B,
			MonitoringPoint.TC_PROBE_11C,
			MonitoringPoint.TC_PROBE_11D,
			MonitoringPoint.TC_PROBE_12A,
			MonitoringPoint.TC_PROBE_12B,
			MonitoringPoint.TC_PROBE_13A,
			MonitoringPoint.TC_PROBE_13B,
			MonitoringPoint.TC_PROBE_13C,
			MonitoringPoint.TC_PROBE_13D,
			MonitoringPoint.TC_PROBE_14A,
			MonitoringPoint.TC_PROBE_14B,
			MonitoringPoint.TC_PROBE_14C,
			MonitoringPoint.TC_PROBE_15A,
			MonitoringPoint.TC_PROBE_15B,
			MonitoringPoint.TC_PROBE_16A,
			MonitoringPoint.CL_STORMWATER_ENTRANCE,
			MonitoringPoint.CL_STORMWATER_EXIT,
			MonitoringPoint.LC_STORMWATER_A_CYN,
			MonitoringPoint.LC_STORMWATER_B_CYN,
			MonitoringPoint.LC_STORMWATER_B4_BASIN_INLET,
			MonitoringPoint.LC_STORMWATER_C_CYN,
			MonitoringPoint.LC_STORMWATER_CLARIFIER_INLET
		];
	}

}