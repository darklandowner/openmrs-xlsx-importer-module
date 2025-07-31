package org.openmrs.module.xlsximport.api.impl;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.api.PatientService;
import org.openmrs.module.xlsximport.api.XlsxImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class XlsxImportServiceImpl implements XlsxImportService {
	@Autowired
	private PatientService patientService;

	@Override
	public void importXlsx(MultipartFile file) {
		try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				int i = 0;
				Cell cell = row.getCell(i++);
				System.out.println("Cell value: " + cell.getStringCellValue());

				Person p = new Person();
				p.setPersonId(Integer.parseInt(cell.getStringCellValue()));

				PersonName personName = new PersonName();
				cell = row.getCell(i++);
				personName.setGivenName(cell.getStringCellValue());
				cell = row.getCell(i++);
				personName.setMiddleName(cell.getStringCellValue());
				cell = row.getCell(i++);
				personName.setFamilyName(cell.getStringCellValue());
				Set<PersonName> names = new HashSet<>();
				names.add(personName);
				p.setNames(names);

				cell = row.getCell(i++);
				p.setGender(cell.getStringCellValue());

				cell = row.getCell(i++);
				p.setBirthdate(cell.getDateCellValue());

				PersonAddress address = new PersonAddress();
				cell = row.getCell(i++);
				address.setCountry(cell.getStringCellValue());
				cell = row.getCell(i++);
				address.setCountyDistrict(cell.getStringCellValue());
				cell = row.getCell(i++);
				address.setCityVillage(cell.getStringCellValue());
				cell = row.getCell(i++);
				address.setAddress1(cell.getStringCellValue());
				Set<PersonAddress> addresses = new HashSet<>();
				addresses.add(address);
				p.setAddresses(addresses);

				patientService.savePatient(new Patient(p));
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to import XLSX", e);
		}
	}
}
