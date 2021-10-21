package be.condorcet.victorlorfevreprojet2.services;

import be.condorcet.victorlorfevreprojet2.entities.Infos;
import be.condorcet.victorlorfevreprojet2.repositories.InfosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class InfosServiceImpl implements InterfInfosService {
    @Autowired
    private InfosRepository infosRepository;

    @Override
    public Infos create(Infos infos) throws Exception {
        infosRepository.save(infos);
        return infos;
    }

    @Override
    public Infos read(Integer id) throws Exception{
        Optional<Infos> oinf = infosRepository.findById(id);
        return oinf.get();
    }

    @Override
    public Infos update(Infos infos) throws Exception {
        Integer id = infos.getIdInfo();
        Infos oldInf = read(id);
        oldInf.setQuantite(infos.getQuantite());
        infosRepository.save(oldInf);
        return  read(oldInf.getIdInfo());
    }

    @Override
    public void delete(Infos infos) throws Exception{
        infosRepository.deleteById(infos.getIdInfo());
    }

}
