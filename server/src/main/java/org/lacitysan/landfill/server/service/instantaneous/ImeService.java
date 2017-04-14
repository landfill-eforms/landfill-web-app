package org.lacitysan.landfill.server.service.instantaneous;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.persistence.dao.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for IME related objects, such as <code>IMENumber</code> and <code>IMEData</code>.
 * @author Alvin Quach
 */
@Service
public class ImeService {

	@Autowired
	ImeNumberDao imeNumberDao;
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	public ImeNumber createUnverified(ImeNumber imeNumber) {
		
		// Use TreeSet to store existing unverified IME numbers so that they are in order by sequence number.
		Set<ImeNumber> existingImeNumbers = new TreeSet<>(); 
		existingImeNumbers.addAll(imeNumberDao.getUnverifiedBySiteAndDateCode(imeNumber.getSite(), imeNumber.getDateCode()));
		
		short i = 1;
		for (ImeNumber existingImeNumber : existingImeNumbers) {
			if (existingImeNumber.getSequence() > i) {
				break;
			}
			i++;
		}
		imeNumber.setSequence(i);
		imeNumber.setStatus(ExceedanceStatus.UNVERIFIED);
		return imeNumberDao.create(imeNumber);
		
	}
	
	// TODO Change this to work with a batch of IME numbers at once for more efficiency.
	public ImeNumber verify(ImeNumber imeNumber) {
		
		if (imeNumber.getStatus() != ExceedanceStatus.UNVERIFIED) {
			return imeNumber;
		}
		else {
			imeNumber.setStatus(ExceedanceStatus.ACTIVE);
		}
		
		short originalSequence = imeNumber.getSequence();

		List<ImeNumber> existingImeNumbers = imeNumberDao.getVerifiedBySiteAndDateCode(imeNumber.getSite(), imeNumber.getDateCode()).stream().sorted().collect(Collectors.toList());

		boolean shift = false;
		short i = 1;
		for (ImeNumber existingImeNumber : existingImeNumbers) {
			if (existingImeNumber.getSequence() > i) {
				break;
			}
			else if (existingImeNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
				shift = true;
				break;
			}
			i++;
		}
		imeNumber.setSequence(i);
		imeNumberDao.update(imeNumber);
		
		if (shift) {
			for (int j = originalSequence - 1; j > i; j--) {
				ImeNumber existingImeNumber = existingImeNumbers.get(j - 1);
				if (existingImeNumber.getSequence() < originalSequence) {
					existingImeNumber.setSequence((short)(existingImeNumber.getSequence() + 1));
					imeNumberDao.update(existingImeNumber);
				}
			}
		}

		return imeNumber;
		
	}

	/**
	 * Generates a formatted string representation of an IME number, based on the IME number's site, date, and sequence number.
	 * The format of the generated number will be AA-yyMM-BB (dashes included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param imeNumber The object that contains the IME number's information. 
	 * @return The formatted IME number string.
	 */
	public String getStringFromImeNumber(ImeNumber imeNumber) {
		return imeNumber.getSite().getShortName() + "-" + String.format("%04d", imeNumber.getDateCode()) + "-" + String.format("%02d", imeNumber.getSequence());
	}

	/**
	 * Creates an <code>IMENumber</code> object from a given IME number string.
	 * The returned object will have a primary key (id) of 0.
	 * The format of the input string must be AA-yyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param imeNumber String representation of an IME number.
	 * @return A generated <code>IMENumber</code> object based on the input IME number string, or <code>null</code> if the input string was not valid.
	 */
	public ImeNumber getImeNumberFromString(String imeNumber) {
		imeNumber = imeNumber.replaceAll("-", "").trim();
		try {
			if (imeNumber.length() != 8) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			Site site = monitoringPointService.getSiteByShortName(imeNumber.substring(0, 2));
			if (site == null) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			int dateCode = Integer.valueOf(imeNumber.substring(2,6)); // TODO Verify that this is valid.
			short sequence = Short.valueOf(imeNumber.substring(6));
			ImeNumber result = new ImeNumber();
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
		
		// Use TreeSet to store existing IME Numbers so that they are in order by sequence number.
		Set<ImeNumber> existing = new TreeSet<>(); 
		existing.addAll(imeNumberDao.getBySiteAndDateCode(site, dateCode));
		
		short last = 1;
		for (ImeNumber imeNumber : existing) {
			if (verifiedOnly && imeNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
				continue;
			}
			if (imeNumber.getSequence() > last) {
				break;
			}
			last++;
		}
		return last;
	}

}
