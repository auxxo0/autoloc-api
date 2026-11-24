package tn.esprit.autoloc.service;

import java.util.List;

public interface IGenericService<T,ID> extends ICheckService<T,ID>, IReadService<T,ID>, IWriteService<T,ID>  {
}