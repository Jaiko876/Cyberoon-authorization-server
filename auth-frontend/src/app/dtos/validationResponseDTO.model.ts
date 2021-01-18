export enum ValidationDTO {
  BadCredentials = 'BAD_CREDENTIALS',
  IllegalArguments = 'ILLEGAL_ARGUMENTS',
  WrongEmail = 'WRONG_EMAIL',
  WrongUsername = 'WRONG_USERNAME',
}

export interface ValidationResponseDTO {
  code: ValidationDTO;
}
