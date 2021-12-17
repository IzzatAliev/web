export interface CourseRequestDto {

  courseName: string;
  credit: number;
  courseType: CourseType;
  description: string;
}

export enum CourseType {

  IT = 'IT',
  MATHEMATICS = 'MATHEMATICS',
  LITERATURE = 'LITERATURE',
  CHEMISTRY = 'CHEMISTRY',
  BIOLOGY = 'BIOLOGY',
  PHYSICS = 'PHYSICS',
  GEOGRAPHY = 'GEOGRAPHY'
}
