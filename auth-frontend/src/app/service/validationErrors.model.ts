import { ValidationDTO } from '../dtos/validationResponseDTO.model';

export const ValidationErrors: ValidationErrors = {
  [ValidationDTO.BadCredentials]: 'Bad credentials error',
  [ValidationDTO.IllegalArguments]: 'Illegal arguments',
  [ValidationDTO.WrongEmail]: 'Email is wrong',
  [ValidationDTO.WrongUsername]: 'Username is wrong',
};

interface ValidationErrors {
  [name: string]: string;
}
