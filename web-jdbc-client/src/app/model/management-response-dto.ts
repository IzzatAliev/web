import {ResponseDto} from "./response-dto";

export interface ManagementResponseDto extends ResponseDto {

  managementName: string;
  staffCount: number;
  courseCount: number;
}
