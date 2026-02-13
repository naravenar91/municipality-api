export interface User {
  id?: number;
  userUuid?: string;
  userName: string;
  name: string;
  email?: string;
  address?: string;
  phone?: string;
  isActive?: boolean;
  createdAt?: string;
}
