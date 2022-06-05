package support.enums;


import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Browser {
	CHROME(DriverManagerType.CHROME),
	FIREFOX(DriverManagerType.FIREFOX),
	API(DriverManagerType.CHROME);

	private DriverManagerType driverType;
}
