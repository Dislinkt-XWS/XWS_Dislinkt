export interface UserDto {
    password: string
    username: string;
    email: string;
    firstName: string;
    lastName: string;
    role: Role;
}

export enum Role {
    ADMIN, USER, OWNER
}