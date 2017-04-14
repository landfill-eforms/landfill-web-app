package org.lacitysan.landfill.server.service.integrated;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.persistence.dao.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.enums.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for ISE related objects, such as <code>ISENumber</code> and <code>ISEData</code>.
 * @author Alvin Quach
 */
@Service
public class IseService {

	@Autowired
	IseNumberDao iseNumberDao;
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	public IseNumber createUnverified(IseNumber iseNumber) {
		
		// Use TreeSet to store existing unverified IME numbers so that they are in order by sequence number.
		Set<IseNumber> existingIseNumbers = new TreeSet<>(); 
		existingIseNumbers.addAll(iseNumberDao.getUnverifiedBySiteAndDateCode(iseNumber.getSite(), iseNumber.getDateCode()));
		
		short i = 1;
		for (IseNumber existingIseNumber : existingIseNumbers) {
			if (existingIseNumber.getSequence() > i) {
				break;
			}
			i++;
		}
		iseNumber.setSequence(i);
		iseNumber.setStatus(ExceedanceStatus.UNVERIFIED);
		return iseNumberDao.create(iseNumber);
		
	}
	
	// TODO Change this to work with a batch of IME numbers at once for more efficiency.
	public IseNumber verify(IseNumber iseNumber) {
		
		if (iseNumber.getStatus() != ExceedanceStatus.UNVERIFIED) {
			return iseNumber;
		}
		else {
			iseNumber.setStatus(ExceedanceStatus.ACTIVE);
		}
		
		short originalSequence = iseNumber.getSequence();

		List<IseNumber> existingIseNumbers = iseNumberDao.getVerifiedBySiteAndDateCode(iseNumber.getSite(), iseNumber.getDateCode()).stream().sorted().collect(Collectors.toList());

		boolean shift = false;
		short i = 1;
		for (IseNumber existingIseNumber : existingIseNumbers) {
			if (existingIseNumber.getSequence() > i) {
				break;
			}
			else if (existingIseNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
				shift = true;
				break;
			}
			i++;
		}
		iseNumber.setSequence(i);
		iseNumberDao.update(iseNumber);
		
		if (shift) {
			for (int j = originalSequence - 1; j > i; j--) {
				IseNumber existingIseNumber = existingIseNumbers.get(j - 1);
				if (existingIseNumber.getSequence() < originalSequence) {
					existingIseNumber.setSequence((short)(existingIseNumber.getSequence() + 1));
					iseNumberDao.update(existingIseNumber);
				}
			}
		}

		return iseNumber;
		
	}

	/**
	 * Generates a formatted string representation of an ISE number, based on the ISE number's site, date, and sequence number.
	 * The format of the generated number will be AA-yyMM-BB (dashes included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param iseNumber The object that contains the ISE number's information. 
	 * @return The formatted ISE number string.
	 */
	public String getStringFromIseNumber(IseNumber iseNumber) {
		return iseNumber.getSite().getShortName() + "-" + String.format("%04d", iseNumber.getDateCode()) + "-" + String.format("%02d", iseNumber.getSequence());
	}

	/**
	 * Creates an <code>ISENumber</code> object from a given ISE number string.
	 * The returned object will have a primary key (id) of 0.
	 * The format of the input string must be AA-yyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param iseNumber String representation of an ISE number.
	 * @return A generated <code>ISENumber</code> object based on the input ISE number string, or <code>null</code> if the input string was not valid.
	 */
	public IseNumber getIseNumberFromString(String iseNumber) {
		iseNumber = iseNumber.replaceAll("-", "").trim();
		try {
			if (iseNumber.length() != 8) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			Site site = monitoringPointService.getSiteByShortName(iseNumber.substring(0, 2));
			if (site == null) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			int dateCode = Integer.valueOf(iseNumber.substring(2,6)); // TODO Verify that this is valid.
			short sequence = Short.valueOf(iseNumber.substring(6));
			IseNumber result = new IseNumber();
			result.setId(0);
			result.setSite(site);
			result.setDateCode(dateCode);
			result.setSequence(sequence);
			return result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public short getNextSequence(Site site, Integer dateCode, boolean verifiedOnly) {
		
		// Use TreeSet to store existing ISE Numbers so that they are in order by sequence number.
		Set<IseNumber> existing = new TreeSet<>(); 
		existing.addAll(iseNumberDao.getBySiteAndDateCode(site, dateCode));
		
		short last = 1;
		for (IseNumber iseNumber : existing) {
			if (verifiedOnly && iseNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
				continue;
			}
			if (iseNumber.getSequence() > last) {
				break;
			}
			last++;
		}
		return last;
	}
	
}
