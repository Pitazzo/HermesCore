package es.programahermes.Utilidades;

public class ModiferConverter {

	public static double Scala(double level) {
		if (level > 50) {
			double modifier = (level / 25) - 1;
			return modifier;
		} else {
			if (level < 50) {
				double modifier = 0.0136 * level + 0.3197;
				return modifier;
			} else {

				return 1;

			}

		}

	}

	public static double SacalaReverse(double level) {
		if (level > 50) {
			double modifier = -((1 / 75) * level) + (5 / 3);
			return modifier;
		} else {
			if (level < 50) {
				double modifier = ((-2 * level) + 149) / 49;
				return modifier;
			} else {
				return 1;
			}
		}

	}

}
