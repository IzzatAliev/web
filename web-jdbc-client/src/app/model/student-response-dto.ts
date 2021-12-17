import {ResponseDto} from "./response-dto";

export interface StudentResponseDto extends ResponseDto{

  firstName: string;
  lastName: string;
  age: number;
  courseCount: number;
}
