export interface UserDto {
    password: string
    username: string;
    email: string;
    firstName: string;
    lastName: string;
}

export interface User {
    id: string;
    password: string;
    username: string;
    email: string;
    firstName: string;
    lastName: string;
    role: Role;
}

export interface Role {
    name: string
}