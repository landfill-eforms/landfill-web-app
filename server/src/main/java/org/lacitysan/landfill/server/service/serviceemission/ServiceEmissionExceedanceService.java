package org.lacitysan.landfill.server.service.serviceemission;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionExceedanceNumber;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles the logical operations for service emissions exceedance (IME and ISE) related classes.
 * @param <T> The type of the service emissions exceedance number (ie. <code>IMENumber</code>).
 * @author Alvin Quach
 */
public abstract class ServiceEmissionExceedanceService<T extends ServiceEmissionExceedanceNumber> {
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	public T createUnverified(T exceedanceNumber) {
		
		// Use TreeSet to store existing unverified IME/ISE numbers so that they are in order by sequence number.
		Set<T> existingSet = new TreeSet<>(); 
		existingSet.addAll(getCrudRepository().getUnverifiedBySiteAndDateCode(exceedanceNumber.getSite(), exceedanceNumber.getDateCode()));
		
		short i = 1;
		for (T existing : existingSet) {
			if (existing.getSequence() > i) {
				break;
			}
			i++;
		}
		
		exceedanceNumber.setSequence(i);
		exceedanceNumber.setStatus(ExceedanceStatus.UNVERIFIED);
		return getCrudRepository().create(exceedanceNumber);
		
	}
	
	// TODO Change this to work with a batch of IME numbers at once for more efficiency.
	public T verify(T exceedanceNumber) {
		if (exceedanceNumber.getStatus() != ExceedanceStatus.UNVERIFIED) {
			return exceedanceNumber;
		}
		else {
			exceedanceNumber.setStatus(ExceedanceStatus.ACTIVE);
		}
		short originalSequence = exceedanceNumber.getSequence();
		List<T> existingImeNumbers = getCrudRepository().getVerifiedBySiteAndDateCode(exceedanceNumber.getSite(), exceedanceNumber.getDateCode()).stream().sorted().collect(Collectors.toList());
		boolean shift = false;
		short i = 1;
		for (T existingImeNumber : existingImeNumbers) {
			if (existingImeNumber.getSequence() > i) {
				break;
			}
			else if (existingImeNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
				shift = true;
				break;
			}
			i++;
		}
		exceedanceNumber.setSequence(i);
		getCrudRepository().update(exceedanceNumber);
		
		if (shift) {
			for (int j = originalSequence - 1; j > i; j--) {
				T existingImeNumber = existingImeNumbers.get(j - 1);
				if (existingImeNumber.getSequence() < originalSequence) {
					existingImeNumber.setSequence((short)(existingImeNumber.getSequence() + 1));
					getCrudRepository().update(existingImeNumber);
				}
			}
		}
		return exceedanceNumber;
	}

	/**
	 * Generates a formatted string representation of a service emissions exceedance number, based on the exceedance number's site, date, and sequence number.
	 * The format of the generated number will be AAyyMM-BB (dash included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param exceedanceNumber The object that contains the service emissions exceedance number's information. 
	 * @return The formatted service emissions exceedance number string.
	 */
	protected String getStringFromExceedanceNumber(T exceedanceNumber) {
		return exceedanceNumber.getSite().getShortName() + String.format("%04d", exceedanceNumber.getDateCode()) + "-" + String.format("%02d", exceedanceNumber.getSequence());
	}

	/**
	 * Creates an object that extends <code>ServiceEmissionsExceedanceNumber</code> using the given service emissions exceedance number string.
	 * The format of the input string must be AAyyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param exceedanceNumber String representation of an service emissions exceedance number.
	 * @return An object extending <code>ServiceEmissionsExceedanceNumber</code> based on the input service emissions exceedance number string, or <code>null</code> if the input string was not valid.
	 */
	protected T getExceedanceNumberFromString(String exceedanceNumber) {
		exceedanceNumber = exceedanceNumber.replaceAll("-", "").trim();
		try {
			if (exceedanceNumber.length() != 8) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			Site site = monitoringPointService.getSiteByShortName(exceedanceNumber.substring(0, 2));
			if (site == null) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			int dateCode = Integer.valueOf(exceedanceNumber.substring(2,6)); // TODO Verify that this is valid.
			short sequence = Short.valueOf(exceedanceNumber.substring(6));
			T result = instantiateExceedanceNumber();
			result.setId(0); // Is this needed?
			result.setSite(site);
			result.setDateCode(dateCode);
			result.setSequence(sequence);
			return result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	abstract protected ServiceEmissionExceedanceNumberDao<T> getCrudRepository();
	
	@SuppressWarnings("unchecked")
	private T instantiateExceedanceNumber() {
		try {
			Class<?> exceedanceNumberGenericClass = (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			return (T)Class.forName(exceedanceNumberGenericClass.getName()).getConstructor().newInstance();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
