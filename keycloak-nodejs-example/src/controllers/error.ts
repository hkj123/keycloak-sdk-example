import { Request, Response } from "express";

/**
 * GET /
 */
export const error = (req: Request, res: Response) => {
  res.render("error", {

  });
};